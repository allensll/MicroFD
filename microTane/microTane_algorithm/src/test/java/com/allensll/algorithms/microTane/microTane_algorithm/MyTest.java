package com.allensll.algorithms.microTane.microTane_algorithm;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTest {

    private microTaneAlgorithm algo;
    private CSVTestCase csvt;

    @Before
    public void setUp() throws Exception {
        this.algo = new microTaneAlgorithm();
        this.csvt = new CSVTestCase();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void executeAndVerifyWithFixture() throws AlgorithmExecutionException {
        this.algo.setRelationalInputConfigurationValue(microTaneAlgorithm.INPUT_TAG, csvt);
        this.algo.setResultReceiver(csvt);
        // Execute functionality
        this.algo.execute();

//        fixture.getFunctionalDependencyResultReceiver()
        // Check Result
    }

}
