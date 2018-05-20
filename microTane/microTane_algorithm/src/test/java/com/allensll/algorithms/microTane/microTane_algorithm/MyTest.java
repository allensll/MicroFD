package com.allensll.algorithms.microTane.microTane_algorithm;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTest {

    private TaneAlgorithm algo2;
    private microTaneAlgorithm algo;
    private CSVTestCase csvt;

    @Before
    public void setUp() throws Exception {
        this.algo = new microTaneAlgorithm();
        this.algo2 = new TaneAlgorithm();
        this.csvt = new CSVTestCase("data2.csv",false);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void executeAndVerifyWithFixture() throws AlgorithmExecutionException {
        this.algo.setRelationalInputConfigurationValue(microTaneAlgorithm.INPUT_TAG, csvt);
        this.algo.setResultReceiver(csvt);
        // Execute functionality

        Long s = System.nanoTime();
        this.algo.execute();
        Long e = System.nanoTime();
        System.out.println((e-s)/1000000000.0);

//        fixture.getFunctionalDependencyResultReceiver()
        // Check Result
    }

    @Test
    public void executeAndVerifyWithFixture2() throws AlgorithmExecutionException {
        this.algo2.setRelationalInputConfigurationValue(microTaneAlgorithm.INPUT_TAG, csvt);
        this.algo2.setResultReceiver(csvt);
        // Execute functionality

        Long s = System.nanoTime();
        this.algo2.execute();
        Long e = System.nanoTime();
        System.out.println((e-s)/1000000000.0);

//        fixture.getFunctionalDependencyResultReceiver()
        // Check Result
    }

}
