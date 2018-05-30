package com.allensll.algorithms.microHyFD;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.algorithm_types.FunctionalDependencyAlgorithm;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.InputIterationException;
import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import com.allensll.algorithms.microHyFD.fixtures.AbaloneFixture;
import com.allensll.algorithms.microHyFD.fixtures.AbstractAlgorithmTestFixture;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture1;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture10;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture11;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture12;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture13;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture14;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture15;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture16;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture17;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture2;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture3;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture4;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture5;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture6;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture7;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture8;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture9;
import com.allensll.algorithms.microHyFD.fixtures.BridgesFixture;

public abstract class FDAlgorithmTest {

	protected FunctionalDependencyAlgorithm algo;
	
	@Before
	public abstract void setUp() throws Exception;

	@After
	public abstract void tearDown() throws Exception;
	
	protected abstract void executeAndVerifyWithFixture(AbstractAlgorithmTestFixture fixture) throws AlgorithmExecutionException;
	protected abstract void executeAndVerifyWithFixture(AbaloneFixture fixture) throws AlgorithmConfigurationException, AlgorithmExecutionException, UnsupportedEncodingException, FileNotFoundException;
	protected abstract void executeAndVerifyWithFixture(BridgesFixture fixture) throws CouldNotReceiveResultException, AlgorithmConfigurationException, AlgorithmExecutionException, UnsupportedEncodingException, FileNotFoundException;
	protected abstract void executeAndVerifyWithFixture(AlgorithmTestFixture fixture) throws AlgorithmConfigurationException, InputGenerationException, InputIterationException, AlgorithmExecutionException;

    @Test 
    public void testExecute1() throws AlgorithmExecutionException {
        AlgorithmTestFixture1 fixture = new AlgorithmTestFixture1();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute2() throws AlgorithmExecutionException {
        AlgorithmTestFixture2 fixture = new AlgorithmTestFixture2();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute3() throws AlgorithmExecutionException {
        AlgorithmTestFixture3 fixture = new AlgorithmTestFixture3();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute4() throws AlgorithmExecutionException {
        AlgorithmTestFixture4 fixture = new AlgorithmTestFixture4();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute5() throws AlgorithmExecutionException {
        AlgorithmTestFixture5 fixture = new AlgorithmTestFixture5();
        executeAndVerifyWithFixture(fixture);
    }

    @Test 
    public void testExecute6() throws AlgorithmExecutionException {
        AlgorithmTestFixture6 fixture = new AlgorithmTestFixture6();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute7() throws AlgorithmExecutionException {
        AlgorithmTestFixture7 fixture = new AlgorithmTestFixture7();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute8() throws AlgorithmExecutionException {
        AlgorithmTestFixture8 fixture = new AlgorithmTestFixture8();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute9() throws AlgorithmExecutionException {
        AlgorithmTestFixture9 fixture = new AlgorithmTestFixture9();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute10() throws AlgorithmExecutionException {
        AlgorithmTestFixture10 fixture = new AlgorithmTestFixture10();
        executeAndVerifyWithFixture(fixture);
    }

    @Test 
    public void testExecute11() throws AlgorithmExecutionException {
        AlgorithmTestFixture11 fixture = new AlgorithmTestFixture11();
        executeAndVerifyWithFixture(fixture);
    }

    @Test 
    public void testExecute12() throws AlgorithmExecutionException {
        AlgorithmTestFixture12 fixture = new AlgorithmTestFixture12();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute13() throws AlgorithmExecutionException {
        AlgorithmTestFixture13 fixture = new AlgorithmTestFixture13();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute14() throws AlgorithmExecutionException {
        AlgorithmTestFixture14 fixture = new AlgorithmTestFixture14();
        executeAndVerifyWithFixture(fixture);
    }

    @Test 
    public void testExecute15() throws AlgorithmExecutionException {
        AlgorithmTestFixture15 fixture = new AlgorithmTestFixture15();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute16() throws AlgorithmExecutionException {
        AlgorithmTestFixture16 fixture = new AlgorithmTestFixture16();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute17() throws AlgorithmExecutionException {
        AlgorithmTestFixture17 fixture = new AlgorithmTestFixture17();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute18() throws AlgorithmExecutionException {
        AlgorithmTestFixture14 fixture = new AlgorithmTestFixture14();
        executeAndVerifyWithFixture(fixture);
    }

    @Test 
    public void testExecute19() throws AlgorithmExecutionException {
        AlgorithmTestFixture15 fixture = new AlgorithmTestFixture15();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test 
    public void testExecute20() throws AlgorithmExecutionException {
        AlgorithmTestFixture16 fixture = new AlgorithmTestFixture16();
        executeAndVerifyWithFixture(fixture);
    }
    
    @Test
    public void testAbaloneFixture() throws AlgorithmExecutionException, UnsupportedEncodingException, FileNotFoundException {
        AbaloneFixture fixture = new AbaloneFixture();
        executeAndVerifyWithFixture(fixture);
    }

	@Test
    public void testBridgesFixture() throws AlgorithmExecutionException, UnsupportedEncodingException, FileNotFoundException {
        BridgesFixture fixture = new BridgesFixture();
        executeAndVerifyWithFixture(fixture);
    }

	@Test
    public void testAlgorithmFixture() throws AlgorithmExecutionException {
        AlgorithmTestFixture fixture = new AlgorithmTestFixture();
        executeAndVerifyWithFixture(fixture);
    }

}
