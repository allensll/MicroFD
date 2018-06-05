package com.allensll.algorithms.microTest;

import com.sun.xml.internal.bind.v2.TODO;
import de.metanome.algorithm_integration.ColumnCombination;
import de.metanome.algorithm_integration.input.InputIterationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.result_receiver.FunctionalDependencyResultReceiver;
import de.metanome.algorithm_integration.results.FunctionalDependency;

import java.util.ArrayList;
import java.util.List;

public class TestCaseOld implements RelationalInput, RelationalInputGenerator, FunctionalDependencyResultReceiver {

    private List<List<FunctionalDependency>> fdsByLevel;
    private List<FunctionalDependency> fdsSingleLhs;
    private int numberOfColumns;
    private boolean isEqualSeg;
    private int numberOfTuples;
    private int indicator;


    public void TestCase(List<FunctionalDependency> fds) {

        this.indicator = 0;
        this.numberOfTuples = fds.size();

        int maxLhs = 0;
        for (FunctionalDependency fd : fds) {
            int numLhs = fd.getDeterminant().getColumnIdentifiers().size();
            if (numLhs > maxLhs) {
                maxLhs = numLhs;
            }
        }

        fdsByLevel = new ArrayList<List<FunctionalDependency>>(maxLhs+1);
        for (int i=0; i<=maxLhs; i++) {
            fdsByLevel.add(new ArrayList<FunctionalDependency>());
        }
        for (FunctionalDependency fd : fds) {
            int numLhs = fd.getDeterminant().getColumnIdentifiers().size();
            fdsByLevel.get(numLhs).add(fd);
        }
        fdsSingleLhs = fdsByLevel.get(1);
    }
    // TODO: how to divide the Lhs
    public int getNumLhs(FunctionalDependency fd) {
        return 0;
    }


    public boolean ifEqualSeg() {
        this.isEqualSeg = true;
        int maxLhs = 0;
        for (FunctionalDependency fd : fdsSingleLhs) {
            int numLhs = fd.getDeterminant().getColumnIdentifiers().size();
            if (numLhs > maxLhs) {
                if (maxLhs != 0) {
                    this.isEqualSeg = false;
                }
                maxLhs = numLhs;
            }
        }
        this.numberOfColumns = maxLhs;
    }

    public void generateMFDInput() {

    }

    public boolean hasNext() throws InputIterationException {
        return indicator <= numberOfTuples;
    }

    public List<String> next() throws InputIterationException{
        ColumnCombination Lhs = this.fdsSingleLhs.get(indicator).getDeterminant();
    }

    public int numberOfColumns() {
        return this.numberOfColumns;
    }

    public String relationName() {

    }

    public List<String> columnNames() {

    }
}
