package com.allensll.algorithms.microDepminer.microDepminer_algorithm;

import com.allensll.algorithms.microDepminer.microDepminer_helper.AlgorithmMetaGroup2;
import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementBoolean;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementRelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;

public class AlgorithmGroup2PrunedDepMiner extends AlgorithmMetaGroup2 {

    @Override
    protected void buildSpecs() {
        configSpecs.add(new ConfigurationRequirementRelationalInput(AlgorithmMetaGroup2.INPUT_TAG));
    	configSpecs.add(new ConfigurationRequirementBoolean(AlgorithmMetaGroup2.USE_OPTIMIZATIONS_TAG));
    }

    @Override
    protected void executeAlgorithm() throws AlgorithmConfigurationException, AlgorithmExecutionException {

        Object opti = this.configurationRequirements.get(AlgorithmMetaGroup2.USE_OPTIMIZATIONS_TAG);
        int numberOfThreads = 1;
        if (opti != null && (Boolean) opti) {
            numberOfThreads = Runtime.getRuntime().availableProcessors();
        }
        Object input = this.configurationRequirements.get(AlgorithmMetaGroup2.INPUT_TAG);
        if (input == null) {
            throw new AlgorithmConfigurationException("No input defined");
        }

        PrunedDepMiner pdm = new PrunedDepMiner(numberOfThreads, this.fdrr);
        pdm.execute(((RelationalInputGenerator) input).generateNewCopy());

    }

    @Override
	public String getAuthors() {
		return PrunedDepMiner.getAuthorName();
	}

	@Override
	public String getDescription() {
		return PrunedDepMiner.getDescriptionText();
	}
}
