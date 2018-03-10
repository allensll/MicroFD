package com.allensll.algorithms.microTane.microTane_tree_dir_algorithm;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import com.allensll.algorithms.microTane.microTane_helper.test_helper.AlgorithmTester;
import com.allensll.algorithms.microTane.microTane_helper.test_helper.fixtures.AbstractAlgorithmTestFixture;
import org.junit.After;
import org.junit.Before;

public class TaneAlgorithmFilterTreeDirectTest extends AlgorithmTester {
    private TaneAlgorithmFilterTreeDirect algo;

    @Before
    public void setUp() throws Exception {
        this.algo = new TaneAlgorithmFilterTreeDirect();
    }

    @After
    public void tearDown() throws Exception {
    }

    protected void executeAndVerifyWithFixture(AbstractAlgorithmTestFixture fixture)
            throws AlgorithmExecutionException {
        this.algo.setRelationalInputConfigurationValue(TaneAlgorithmFilterTreeDirect.INPUT_TAG, fixture.getInputGenerator());
        this.algo.setResultReceiver(fixture.getFunctionalDependencyResultReceiver());
        // Execute functionality
        this.algo.execute();

        // Check Results
        fixture.verifyFunctionalDependencyResultReceiver();
    }
}
