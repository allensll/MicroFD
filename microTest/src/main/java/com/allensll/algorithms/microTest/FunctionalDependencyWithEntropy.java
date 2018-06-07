package com.allensll.algorithms.microTest;

import de.metanome.algorithm_integration.ColumnCombination;
import de.metanome.algorithm_integration.ColumnIdentifier;
import de.metanome.algorithm_integration.results.FunctionalDependency;

public class FunctionalDependencyWithEntropy extends FunctionalDependency implements Comparable<FunctionalDependencyWithEntropy> {

    private double entropy;

    public FunctionalDependencyWithEntropy() {
        super();
    }

    public FunctionalDependencyWithEntropy(ColumnCombination determinant, ColumnIdentifier dependant) {

        super(determinant, dependant);
    }

    public void setEntropy(double entropy) {

        this.entropy = entropy;
    }

    public double getEntropy() {

        return this.entropy;
    }

    public int compareTo(FunctionalDependencyWithEntropy fd) {

        return (int)(this.entropy - fd.entropy);
    }

    @Override
    public String toString() {

        return this.determinant.toString() + "-->" + this.dependant.toString() + "  |  " + String.valueOf(this.entropy);
    }

}
