package com.allensll.algorithms.microTest;

import de.metanome.algorithm_integration.ColumnIdentifier;
import de.metanome.algorithm_integration.results.FunctionalDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataWrapper {

    private CSVTestCase csvt;
    private String filename;

    private boolean[] isEqualDivd;
    private int[] maxLhs;


    public DataWrapper() {

        this("2018-06-05_17-31-01_1000.csv");
    }

    public DataWrapper(String filename) {

        this.filename = filename;
        try {
            this.setCsvt();
        } catch (Exception e) {
            System.out.print("set csvt error");
        }
    }

    private void setCsvt() throws Exception {

        setCsvt(this.filename, false);
    }

    private void setCsvt(String filename, boolean hasHeader) throws Exception {
        this.csvt = new CSVTestCase(filename, hasHeader);
    }

    public void ifEqualDivd() {

        this.isEqualDivd = new boolean[this.csvt.numberOfColumns()];
        this.maxLhs = new int[this.csvt.numberOfColumns()];
        for (int i=0; i<this.csvt.numberOfColumns(); i++) {
            List<String> values = this.csvt.getRecordsByCol(i);
            boolean isEqual = true;
            int maxLhs = 0;
            for (String value : values) {
                int numLhs = value.length();
                if (numLhs > maxLhs) {
                    if (maxLhs != 0) {
                        isEqual = false;
                    }
                    maxLhs = numLhs;
                }
            }
            this.maxLhs[i] = maxLhs;
            this.isEqualDivd[i] = isEqual;
        }

    }

    public List<String> divideString(String value) {
        List<String> result = new ArrayList<>();
        String[] values = value.split("");
        for (int i=0; i<value.length(); i++) {
            result.add(values[i]);
        }
        return result;
    }

    public List<List<String>> generateMFDInput(FunctionalDependency fd) {

        Set<ColumnIdentifier> Lhs = fd.getDeterminant().getColumnIdentifiers();
        int rhs = Integer.parseInt(fd.getDependant().getColumnIdentifier());
        int[] lhs = new int[Lhs.size()];
        int idx = 0;
        for (ColumnIdentifier name : Lhs) {
            lhs[idx] = Integer.parseInt(name.getColumnIdentifier());
            idx++;
        }
        List<List<String>> newRelation = new ArrayList<>();
        for (int i=0; i< this.csvt.getNumberOfTuples(); i++) {
            List<String> newTuple = new ArrayList<>(Lhs.size());
            for (int j=0; j<Lhs.size(); j++) {
                String value = this.csvt.getValue(lhs[j]-1, i);
                newTuple.addAll(divideString(value));

            }
            newTuple.add(this.csvt.getValue(rhs-1, i));
            newRelation.add(newTuple);
        }
        return newRelation;
    }

    // TODO: only process equal divided Lhs, how others ?
    public List<TestCase> generateAllSigleLhsMFDInput() {
        ifEqualDivd();
        List<FunctionalDependency> fdsSingleLhs = this.csvt.getFdsByLevel(1);
        List<TestCase>  ts = new ArrayList<>();
        for (FunctionalDependency fd : fdsSingleLhs) {
            ColumnIdentifier col = (ColumnIdentifier)fd.getDeterminant().getColumnIdentifiers().toArray()[0];
            String colName = col.getColumnIdentifier();
            if (!this.isEqualDivd[Integer.parseInt(colName)-1]) {
                continue;
            }
            List<List<String>> records = generateMFDInput(fd);
            ts.add(new TestCase(fd, records, null));
        }
        return ts;
    }

    public CSVTestCase getCsvt() {
        return this.csvt;
    }
}
