package com.allensll.algorithms.microHyFD;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTest {

    private HyFD algo;
    private CSVTestCase csvt;

    @Before
    public void setUp() throws Exception {
        System.out.print("11111111");
//        this.algo = new HyFD();
//        this.csvt = new CSVTestCase("data1.csv",false);
        this.csvt = new CSVTestCase();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void executeAndVerifyWithFixture() throws AlgorithmExecutionException {
        System.out.print("11111111");
        if (csvt == null) {
            System.out.print("null");
        }

        this.algo.setRelationalInputConfigurationValue(HyFD.Identifier.INPUT_GENERATOR.name(), csvt);
        this.algo.setResultReceiver(csvt);
        // Execute functionality

        Long s = System.nanoTime();
        this.algo.execute();
        Long e = System.nanoTime();
        System.out.println((e-s)/1000000000.0);

//        fixture.getFunctionalDependencyResultReceiver()
        // Check Result
    }

}
