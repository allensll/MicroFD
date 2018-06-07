package com.allensll.algorithms.microTest;

import de.metanome.algorithm_integration.results.FunctionalDependency;

public class FunctionalDependencyWithEntropy2 implements Comparable<FunctionalDependencyWithEntropy2> {

    private FunctionalDependency fd;
    private double entropy;

    public FunctionalDependencyWithEntropy2(FunctionalDependency fd, double entropy) {

        this.fd = fd;
        this.entropy = entropy;
    }

    public void setEntropy(double entropy) {

        this.entropy = entropy;
    }

    public double getEntropy() {

        return this.entropy;
    }

    public int compareTo(FunctionalDependencyWithEntropy2 fd) {

        double epsilon = 0.001;
        double diff = fd.entropy - this.entropy;
        if (diff > epsilon) {
            return 1;
        } else if (diff < -epsilon) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {

        return this.fd.getDeterminant().toString() + "-->" + this.fd.getDependant().toString() + "  |  " + String.valueOf(this.entropy);
    }

}
