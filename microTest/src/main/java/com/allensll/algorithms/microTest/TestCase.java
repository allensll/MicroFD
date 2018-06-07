package com.allensll.algorithms.microTest;

import com.google.common.collect.ImmutableList;
import de.metanome.algorithm_integration.ColumnIdentifier;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.InputIterationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.metanome.algorithm_integration.result_receiver.FunctionalDependencyResultReceiver;
import de.metanome.algorithm_integration.results.FunctionalDependency;
import sun.tracing.dtrace.DTraceProviderFactory;

import java.io.*;
import java.util.*;

public class TestCase implements RelationalInput, RelationalInputGenerator, FunctionalDependencyResultReceiver {

    private static String filePath = "D:\\dataset\\0606\\result\\";
    private String resultFileName = "result.csv";
    private BufferedWriter bw;
    private String fileName;
    private List<String> names;
    private int numberOfColumns;

    private List<List<String>> relation;
    private int numOfColumns;
    private int numOfTuples;
    private int indicator;
    private String relationName;

    private List<FunctionalDependency> fds;
    private List<FunctionalDependencyWithEntropy2> fdes;

    public TestCase(FunctionalDependency fd, List<List<String>> relation, String resultfile) {

        this.relation = relation;
        this.indicator = 0;
        this.numberOfColumns = relation.get(0).size();
        this.numOfTuples = relation.size();
        this.resultFileName = resultfile;
        this.fds = new ArrayList<>();

        List<String> names = new ArrayList<>(this.numberOfColumns);
        for (int i=0; i<this.numberOfColumns; i++) {
            names.add("m"+String.valueOf(i+1));
        }
        this.names = names;

        setRelationName(fd);
        this.resultFileName = this.relationName + ".csv";
    }

    public void close() {

    }

    public void setRelationName(FunctionalDependency fd) {

        Object[] lhs = fd.getDeterminant().getColumnIdentifiers().toArray();
        String relationName = "";
        for (Object o : lhs) {
            relationName += ((ColumnIdentifier)o).getColumnIdentifier();
            relationName += "+";
        }
        relationName += "--" + fd.getDependant().getColumnIdentifier();
        this.relationName = relationName;
    }


    @Override
    public List<String> columnNames() {

        return this.names;
    }

    @Override
    public boolean hasNext() throws InputIterationException {

        return this.indicator < this.numberOfColumns;
    }

    @Override
    public List<String> next() throws InputIterationException {

        if (this.hasNext()) {
            List<String> result = this.relation.get(this.indicator);
            this.indicator++;
            return result;
        } else {
            throw new InputIterationException("in the end");
        }
    }

    @Override
    public int numberOfColumns() {

        return this.numberOfColumns;
    }

    @Override
    public String relationName() {

        return this.relationName;
    }

    @Override
    public RelationalInput generateNewCopy() throws InputGenerationException {

        return this;
    }

    @Override
    public void receiveResult(FunctionalDependency fd) throws CouldNotReceiveResultException {

        System.out.println(fd.getDeterminant() + "-->" + fd.getDependant());
        if (fd.getDependant().getColumnIdentifier().equals("m"+String.valueOf(this.numberOfColumns))) {
            this.fds.add(fd);
        }
    }

    @Override
    public Boolean acceptedResult(FunctionalDependency arg0) {
        return new Boolean(true);
    }

    public boolean saveResult() {
        setFdes();
        try {
            this.bw = new BufferedWriter(new FileWriter(new File(TestCase.filePath + resultFileName)));
            // TODO: save result to file

            for (FunctionalDependencyWithEntropy2 fde : this.fdes) {
                String fdeString = fde.toString();
                this.bw.write(fdeString, 0, fdeString.length());
                this.bw.newLine();
            }
            this.bw.flush();
            this.bw.close();
        } catch (IOException e) {
            System.out.print("save error");
            return false;
        }
        return true;
    }

    public void setFdes() {

        this.fdes = new ArrayList<>();
        for(int i=0; i<fds.size(); i++) {
            FunctionalDependency fd = this.fds.get(i);
            double e = countEntropy(fd);
            FunctionalDependencyWithEntropy2 fde = new FunctionalDependencyWithEntropy2(fd, e);
            this.fdes.add(fde);
        }
        Collections.sort(this.fdes);
    }

    public double countEntropy(FunctionalDependency fd) {
        // TODO: count entropy
        Set<ColumnIdentifier> Lhs = fd.getDeterminant().getColumnIdentifiers();
        int rhs = Integer.parseInt(fd.getDependant().getColumnIdentifier().replace("m", ""))-1;
        int[] lhs = new int[Lhs.size()];
        int idx = 0;
        for (ColumnIdentifier name : Lhs) {
            lhs[idx] = Integer.parseInt(name.getColumnIdentifier().replace("m", ""))-1;
            idx++;
        }
        Map<String, Integer> pli = new HashMap<>();
        for (List<String> record : this.relation) {
            String value = null;
            for  (int i=0; i<lhs.length; i++) {
                value += record.get(lhs[i]);
            }
            if (pli.containsKey(value)) {
                pli.put(value, pli.get(value)+1);
            } else {
                pli.put(value, 1);
            }
        }
        int sum = 0;
        for (int n : pli.values()) {
            sum += n;
        }
        assert (this.numOfTuples == sum);

        double entropy = 0.0;
        for (int n : pli.values()) {
            double p = (double) n / this.numOfTuples;
            entropy -= p * Math.log(p) / Math.log((double)2);
        }
        return entropy;
    }
}
