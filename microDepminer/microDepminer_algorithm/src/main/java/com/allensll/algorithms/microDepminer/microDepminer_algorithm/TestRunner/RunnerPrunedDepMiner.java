package com.allensll.algorithms.microDepminer.microDepminer_algorithm.TestRunner;

import com.allensll.algorithms.microDepminer.microDepminer_helper.testRunner.AlgorithExecuteThread;
import com.allensll.algorithms.microDepminer.microDepminer_helper.testRunner.CSVTestCase;
import com.allensll.algorithms.microDepminer.microDepminer_helper.testRunner.MemorySniffingThread;
import com.allensll.algorithms.microDepminer.microDepminer_algorithm.AlgorithmGroup2PrunedDepMiner;

import java.io.IOException;

public class RunnerPrunedDepMiner {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {

        CSVTestCase.init();

        String fileName = "data2.csv";

        CSVTestCase.writeToResultFile("###Starte: " + fileName);

        String content = "";

        CSVTestCase csvt = null;
        try {
//                csvt = new CSVTestCase(fileName, !(fileName.contains("error") || fileName.contains("request")));
            csvt = new CSVTestCase(fileName, false);
        } catch (IOException e) {
            content = fileName + ";" + "Fehler bei TestCase-Erstellung";
        }

        if (csvt != null) {

            MemorySniffingThread mst = new MemorySniffingThread();
            mst.start();

            AlgorithExecuteThread aet = new AlgorithExecuteThread(new AlgorithmGroup2PrunedDepMiner(), csvt);
            aet.start();

            while (aet.isAlive()) {

                try {
                    Thread.sleep(10000l);
                } catch (InterruptedException e) {
                }

//					Runtime.getRuntime().gc();
                long availableMem = Runtime.getRuntime().freeMemory();

                // 200mb
                if (availableMem < 210000000) {
                    System.out.println(availableMem / 1024 / 1024);
                    content = fileName + ";" + "MemoryOverflow";
                    aet.stop();
                }

            }

            if (aet.e != null) {
                content = fileName + ";" + "Fehler bei der Ausführung des Testcases";
            }

            mst.stopThis();
            try {
                mst.join();
            } catch (InterruptedException e) {
                content = fileName + ";" + "MemoryMessung unvollständig";
            }

            if (content.equals("")) {
                content = fileName + ";" + aet.time + ";" + mst.memPeak;
            }
        }

        CSVTestCase.writeToResultFile(content);
        System.out.println("Finished: " + fileName);

        csvt.close();

    }
}
