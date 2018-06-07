package com.allensll.algorithms.microTest;

import com.allensll.algorithms.microHyFD.HyFD;
import de.metanome.algorithm_integration.AlgorithmExecutionException;

import java.util.List;

public class mFD {

    private HyFD algo;
    private DataWrapper dataWrapper;
    private List<TestCase> mfdt;

    public mFD() throws Exception {
        this.algo = new HyFD();
        this.dataWrapper = new DataWrapper("2018-06-05_17-31-01_1000.csv");
    }


    public void execute() throws AlgorithmExecutionException {
        this.algo.setRelationalInputConfigurationValue(HyFD.Identifier.INPUT_GENERATOR.name(), this.dataWrapper.getCsvt());
        this.algo.setResultReceiver(this.dataWrapper.getCsvt());
        // Execute functionality

        this.algo.execute();

        this.mfdt = this.dataWrapper.generateAllSigleLhsMFDInput();

        for (TestCase tc : this.mfdt) {
            this.algo.setRelationalInputConfigurationValue(HyFD.Identifier.INPUT_GENERATOR.name(), tc);
            this.algo.setResultReceiver(tc);
            this.algo.execute();
            tc.saveResult();
        }
//        Long s = System.nanoTime();
//
//        Long e = System.nanoTime();
//        System.out.println((e-s)/1000000000.0);
    }

    public static void main(String[] args) throws Exception {

        mFD mfd = new mFD();
        mfd.execute();
    }
}
