package com.allensll.algorithms.microTest;

import com.google.common.collect.ImmutableList;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.InputIterationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.metanome.algorithm_integration.result_receiver.FunctionalDependencyResultReceiver;
import de.metanome.algorithm_integration.results.FunctionalDependency;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

public class CSVTestCase implements RelationalInput, RelationalInputGenerator, FunctionalDependencyResultReceiver {

    private static String filePath = "D:\\dataset\\0606\\data\\";
    private static String defaultFileName = "2018-06-05_17-31-01_1000.csv";
    private static boolean defaultHasHeader = false;
    private static BufferedWriter bw;
    private String fileName;
    private int numberOfColumns;
    private int numberOfTuples;
    private ImmutableList<String> names;

    private List<List<FunctionalDependency>> fds;
    private List<List<String>> recordsByCol;
    private List<List<String>> records;
    private int nextRecord;
    private int maxLhs;

    public CSVTestCase() throws IOException {

        this(CSVTestCase.defaultFileName, CSVTestCase.defaultHasHeader);
    }

    public CSVTestCase(String fileName, boolean hasHeader) throws IOException {

        this.fileName = fileName;

        Reader reader = new BufferedReader(new FileReader(new File(CSVTestCase.filePath + fileName)));
        List<CSVRecord> records;
        CSVParser csvParser;
        if (hasHeader) {
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        } else {
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        }
        records = csvParser.getRecords();
        this.numberOfTuples = records.size();
        this.numberOfColumns = records.get(0).size();
        this.nextRecord = 0;

        this.records = new ArrayList<>(this.numberOfTuples);
        this.recordsByCol = new ArrayList<>((this.numberOfColumns));
        for (int i=0; i<this.numberOfColumns; i++) {
            this.recordsByCol.add(new ArrayList<>(this.numberOfTuples));
        }
        for (int i=0; i<this.numberOfTuples; i++) {
            CSVRecord record = records.get(i);
            List<String> newTuple = new ArrayList<>();
            for (int j=0; j<this.numberOfColumns; j++) {
                newTuple.add(record.get(j));
                this.recordsByCol.get(j).add(record.get(j));
            }
            this.records.add(newTuple);
        }

        ImmutableList.Builder<String> builder = new ImmutableList.Builder<String>();
        if (hasHeader) {
            // TODO: add columns names when without header
//            Map<String, Integer> header = csvParser.getHeaderMap();
//            for (int i=0; i<this.numberOfColumns; i++) {
//                builder.add()
//            }
        } else {
            for (int i=0; i<this.numberOfColumns; i++) {
                builder.add(String.valueOf(i+1));
            }
        }
        this.names = builder.build();

        this.fds = new ArrayList<List<FunctionalDependency>>(this.numberOfColumns+1);
        for (int i=0; i<=this.numberOfColumns; i++) {
            fds.add(new ArrayList<FunctionalDependency>());
        }
    }

    public void close() throws IOException {

        if (CSVTestCase.bw != null) {
            CSVTestCase.bw.close();
        }
    }

    @Override
    public List<String> columnNames() {

        return this.names;
    }

    @Override
    public boolean hasNext() throws InputIterationException {

        return this.nextRecord < this.numberOfTuples;
    }

    @Override
    public List<String> next() throws InputIterationException {

        if (this.hasNext()) {
            List<String> result = this.records.get(this.nextRecord);
            this.nextRecord++;
            return result;
        } else {
            throw new InputIterationException("in the end");
        }
    }

    @Override
    public int numberOfColumns() {

        return this.numberOfColumns;
    }

    @Override
    public String relationName() {

        return this.fileName.split("\\.")[0];
    }

    @Override
    public RelationalInput generateNewCopy() throws InputGenerationException {

        return this;
    }

    @Override
    public void receiveResult(FunctionalDependency fd) throws CouldNotReceiveResultException {

         System.out.println(fd.getDeterminant() + "-->" + fd.getDependant());
         int numLhs = fd.getDeterminant().getColumnIdentifiers().size();
         this.fds.get(numLhs).add(fd);
    }

    @Override
    public Boolean acceptedResult(FunctionalDependency arg0) {
        return new Boolean(true);
    }

    public List<FunctionalDependency> getFdsByLevel(int numLhs) {

        return this.fds.get(numLhs);
    }

    public List<List<FunctionalDependency>> getFds() {

        return this.fds;
    }

    public List<List<String>> getRecords() {

        return this.records;
    }

    public List<String> getRecordsByCol(int col) {

        return this.recordsByCol.get(col);
    }

    public int getNumberOfTuples() {
        return this.numberOfTuples;
    }

    public String getValue(int col, int tuple) {

        return this.recordsByCol.get(col).get(tuple);
    }
}

