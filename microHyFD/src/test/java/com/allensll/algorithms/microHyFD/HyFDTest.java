package com.allensll.algorithms.microHyFD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.allensll.algorithms.microHyFD.fixtures.AbaloneFixture;
import com.allensll.algorithms.microHyFD.fixtures.AbstractAlgorithmTestFixture;
import com.allensll.algorithms.microHyFD.fixtures.AlgorithmTestFixture;
import com.allensll.algorithms.microHyFD.fixtures.BridgesFixture;
import org.junit.After;
import org.junit.Before;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.uni_potsdam.hpi.utils.FileUtils;

public class HyFDTest extends FDAlgorithmTest {
	
	private String tempFolderPath = "io" + File.separator + "temp_junit" + File.separator;
	private boolean nullEqualsNull = true;
	
	@Before
	public void setUp() throws Exception {
		this.algo = new HyFD();
	}

	@After
	public void tearDown() throws Exception {
		// Clean temp if there are files from previous runs that may pollute this run
		FileUtils.cleanDirectory(new File(this.tempFolderPath));
	}
	
	protected void executeAndVerifyWithFixture(AbstractAlgorithmTestFixture fixture) throws AlgorithmExecutionException {
		HyFD hyFD = (HyFD) this.algo;
		hyFD.setRelationalInputConfigurationValue(HyFD.Identifier.INPUT_GENERATOR.name(), fixture.getInputGenerator());
		hyFD.setBooleanConfigurationValue(HyFD.Identifier.NULL_EQUALS_NULL.name(), this.nullEqualsNull );
		hyFD.setResultReceiver(fixture.getFunctionalDependencyResultReceiver());
        
		// Execute algorithm
		hyFD.execute();
        
        // Check results
        fixture.verifyFunctionalDependencyResultReceiver();
	}

	protected void executeAndVerifyWithFixture(AbaloneFixture fixture) throws AlgorithmExecutionException, UnsupportedEncodingException, FileNotFoundException {
		HyFD hyFD = (HyFD) this.algo;
		hyFD.setRelationalInputConfigurationValue(HyFD.Identifier.INPUT_GENERATOR.name(), fixture.getInputGenerator());
		hyFD.setBooleanConfigurationValue(HyFD.Identifier.NULL_EQUALS_NULL.name(), this.nullEqualsNull );
		hyFD.setResultReceiver(fixture.getFdResultReceiver());
		
		// Execute functionality
		hyFD.execute();

        // Check Results
        fixture.verifyFdResultReceiver();
	}

	protected void executeAndVerifyWithFixture(BridgesFixture fixture) throws AlgorithmExecutionException, UnsupportedEncodingException, FileNotFoundException {
		HyFD hyFD = (HyFD) this.algo;
		hyFD.setRelationalInputConfigurationValue(HyFD.Identifier.INPUT_GENERATOR.name(), fixture.getInputGenerator());
		hyFD.setBooleanConfigurationValue(HyFD.Identifier.NULL_EQUALS_NULL.name(), this.nullEqualsNull );
		hyFD.setResultReceiver(fixture.getFdResultReceiver());
		
		// Execute functionality
		hyFD.execute();

        // Check Results
        fixture.verifyFunctionalDependencyResultReceiver();
	}

	protected void executeAndVerifyWithFixture(AlgorithmTestFixture fixture) throws AlgorithmExecutionException {
		HyFD hyFD = (HyFD) this.algo;
		hyFD.setRelationalInputConfigurationValue(HyFD.Identifier.INPUT_GENERATOR.name(), fixture.getInputGenerator());
		hyFD.setBooleanConfigurationValue(HyFD.Identifier.NULL_EQUALS_NULL.name(), this.nullEqualsNull );
		hyFD.setResultReceiver(fixture.getFunctionalDependencyResultReceiver());
		
		// Execute functionality
		hyFD.execute();

        // Check Results
        fixture.verifyFunctionalDependencyResultReceiver();
	}
}
