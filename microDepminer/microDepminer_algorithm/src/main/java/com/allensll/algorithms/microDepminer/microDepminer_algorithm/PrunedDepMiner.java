package com.allensll.algorithms.microDepminer.microDepminer_algorithm;

/**
 * Created by sunlonglong on 2018/1/27.
 */

import com.allensll.algorithms.microDepminer.microDepminer_algorithm.modules.Pruned_CMAX_SET_Generator;
import com.allensll.algorithms.microDepminer.microDepminer_algorithm.modules.FunctionalDependencyGenerator;
import com.allensll.algorithms.microDepminer.microDepminer_algorithm.modules.LeftHandSideGenerator;
import com.allensll.algorithms.microDepminer.microDepminer_helper.modules.AgreeSetGenerator;
import com.allensll.algorithms.microDepminer.microDepminer_helper.modules.StrippedPartitionGenerator;
import com.allensll.algorithms.microDepminer.microDepminer_helper.modules.container.AgreeSet;
import com.allensll.algorithms.microDepminer.microDepminer_helper.modules.container.CMAX_SET;
import com.allensll.algorithms.microDepminer.microDepminer_helper.modules.container.StrippedPartition;
import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.result_receiver.FunctionalDependencyResultReceiver;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import org.apache.lucene.util.OpenBitSet;

import java.util.List;

public class PrunedDepMiner {

    private int numberOfThreads;
    private FunctionalDependencyResultReceiver fdrr;

    public PrunedDepMiner(int numberOfThreads, FunctionalDependencyResultReceiver resultReceivers) {

        this.numberOfThreads = numberOfThreads;
        this.fdrr = resultReceivers;

    }

    public void execute(RelationalInput input) throws AlgorithmExecutionException {

        StrippedPartitionGenerator spg = new StrippedPartitionGenerator(numberOfThreads);
        List<StrippedPartition> strippedPartitions = spg.execute(input);
        int length = input.numberOfColumns();

        List<AgreeSet> agreeSets = new AgreeSetGenerator(this.numberOfThreads).execute(strippedPartitions);

        List<CMAX_SET> cmaxSets = new Pruned_CMAX_SET_Generator(this.numberOfThreads, agreeSets, length, length-1).execute();

//        System.out.println(cmaxSets.get(0));

//        List<CMAX_SET> cmaxSet = new LinkedList<>();
//        cmaxSet.add(cmaxSets.get(0));


        Int2ObjectMap<List<OpenBitSet>> lhss = new LeftHandSideGenerator(this.numberOfThreads).execute(cmaxSets, length);

        new FunctionalDependencyGenerator(fdrr, input.relationName(), input.columnNames(), this.numberOfThreads, lhss).execute();

    }

    public static String getAuthorName() {
        return "Tommy Neubert, Martin Schönberg";
    }

    public static String getDescriptionText() {
        return "Difference- and Agree-Set-based FD discovery";
    }
}

