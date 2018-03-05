package com.allensll.algorithms.microDepminer.microDepminer_algorithm;


import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import com.allensll.algorithms.microDepminer.microDepminer_helper.AlgorithmMetaGroup2;

public class AlgorithmGroup2PrunedDepMiner8Kerne extends AlgorithmMetaGroup2 {

    @Override
    protected void buildSpecs() {

        // TODO Auto-generated method stub

    }

    @Override
    protected void executeAlgorithm() throws AlgorithmConfigurationException, AlgorithmExecutionException {

        Object opti = this.configurationRequirements.get(AlgorithmMetaGroup2.USE_OPTIMIZATIONS_TAG);
        int numberOfThreads = 8;
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
