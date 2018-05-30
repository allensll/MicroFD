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

        Long start = System.nanoTime();

        StrippedPartitionGenerator spg = new StrippedPartitionGenerator(numberOfThreads);
        List<StrippedPartition> strippedPartitions = spg.execute(input);

        Long end = System.nanoTime();
        System.out.print("generatorSP: ");
        System.out.println((end-start)/1000000000);
//      ---------------------------------------------------------------------
        int length = input.numberOfColumns();

        start = System.nanoTime();

        List<AgreeSet> agreeSets = new AgreeSetGenerator(this.numberOfThreads).execute(strippedPartitions);

        end = System.nanoTime();
        System.out.print("generatorAgreeSet: ");
        System.out.println((end-start)/1000000000);
//      --------------------------------------------------------------------------

        start = System.nanoTime();

        List<CMAX_SET> cmaxSets = new Pruned_CMAX_SET_Generator(this.numberOfThreads, agreeSets, length, length-1).execute();

        end = System.nanoTime();
        System.out.print("generatorCMAXSet: ");
        System.out.println((end-start)/1000000000);

//      --------------------------------------------------------------------------

        start = System.nanoTime();

        Int2ObjectMap<List<OpenBitSet>> lhss = new LeftHandSideGenerator(this.numberOfThreads).execute(cmaxSets, length);

        end = System.nanoTime();
        System.out.print("generatorLeftHand: ");
        System.out.println((end-start)/1000000000);

//      --------------------------------------------------------------------------

        new FunctionalDependencyGenerator(fdrr, input.relationName(), input.columnNames(), this.numberOfThreads, lhss).execute();

    }

    public static String getAuthorName() {
        return "Tommy Neubert, Martin Schönberg";
    }

    public static String getDescriptionText() {
        return "Difference- and Agree-Set-based FD discovery";
    }
}

