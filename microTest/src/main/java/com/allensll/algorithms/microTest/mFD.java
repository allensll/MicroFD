package com.allensll.algorithms.microTest;

import com.allensll.algorithms.microHyFD.HyFD;
import de.metanome.algorithm_integration.AlgorithmExecutionException;

public class mFD {

    private HyFD algo;
    private CSVTestCase csvt;

    public void setUp() throws Exception {
        this.algo = new HyFD();
        this.csvt = new CSVTestCase("data3.csv",false);
    }

    public

    public void execute() throws AlgorithmExecutionException {
        if (csvt == null) {
            System.out.print("null input");
        }

        this.algo.setRelationalInputConfigurationValue(HyFD.Identifier.INPUT_GENERATOR.name(), csvt);
        this.algo.setResultReceiver(csvt);
        // Execute functionality

        this.algo.execute();

        csvt.getFds();

        Long s = System.nanoTime();

        Long e = System.nanoTime();
        System.out.println((e-s)/1000000000.0);
    }

    public static void main(String[] args) throws Exception {
        mFD mfd = new mFD();
        mfd.setUp();
        mfd.execute();
    }
}
