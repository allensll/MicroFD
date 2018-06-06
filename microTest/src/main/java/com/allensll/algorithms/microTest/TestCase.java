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

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        try {
            this.bw = new BufferedWriter(new FileWriter(new File(TestCase.filePath + resultFileName)));
            // TODO: save result to file

            this.bw.close();
        } catch (IOException e) {
            System.out.print("save error");
            return false;
        }
        return true;
    }

    public double countEntropy(FunctionalDependency fd) {
        // TODO: count entropy
        return 0.0;
    }
}
