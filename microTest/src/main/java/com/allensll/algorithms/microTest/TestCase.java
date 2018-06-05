package com.allensll.algorithms.microTest;

import com.google.common.collect.ImmutableList;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.InputIterationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.metanome.algorithm_integration.result_receiver.FunctionalDependencyResultReceiver;
import de.metanome.algorithm_integration.results.FunctionalDependency;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestCase implements RelationalInput, RelationalInputGenerator, FunctionalDependencyResultReceiver {

    private static String filePath = "D:\\works\\1111\\Metanome-1.1\\backend\\WEB-INF\\classes\\inputData\\";
    private static String defaultFileName = "data1.csv";
    private static boolean defaultHasHeader = false;
    private static BufferedWriter bw;
    private BufferedReader br;
    private boolean hasHeader;
    private String fileName;
    private String nextLine;
    private int numberOfColumns;
    private ImmutableList<String> names;
    private String delimiter;

    private List<List<String>> relation;
    private int numOfColumns;
    private int numOfTuples;
    private int indicator;
    private String relationName;

    private List<FunctionalDependency> fds;

    public TestCase() throws IOException {

        this(TestCase.defaultFileName, TestCase.defaultHasHeader);
    }

    public TestCase(String fileName, boolean hasHeader) throws IOException {

        this.fileName = fileName;
        this.hasHeader = hasHeader;

        this.br = new BufferedReader(new FileReader(new File(TestCase.filePath + fileName)));
        this.nextLine = this.br.readLine();

        if (this.nextLine.split(",").length > this.nextLine.split(";").length) {
            this.delimiter = ",";
        } else {
            this.delimiter = ";";
        }

        this.calcNumbers();
        this.getNames();

        this.fds = new ArrayList<>();
    }

    public TestCase(FunctionalDependency fd, List<List<String>> relation) {

        this.relationName = fd.toString();
        this.relation = relation;
        this.indicator = 0;
        this.numberOfColumns = relation.get(0).size();
        this.numOfTuples = relation.size();


    }

//    public static List<String> getAllFileNames() {
//
//        File[] fa = new File(TestCase.filePath).listFiles();
//
//        List<String> result = new LinkedList<String>();
//        for (File f : fa) {
//
//            if (f.getName().contains(".csv")) {
//                result.add(f.getName());
//            }
//
//        }
//
//        return result;
//
//    }
//
//    public static void writeToResultFile(String s) throws IOException {
//
//        bw.write(s);
//        bw.newLine();
//        bw.flush();
//    }
//
//    public static void init() throws IOException {
//
//        TestCase.bw = new BufferedWriter(new FileWriter("Result" + System.currentTimeMillis() + ".csv"));
//        bw.write("file;time;mem");
//        bw.newLine();
//        bw.flush();
//    }
//
//    public void close() throws IOException {
//
//        if (TestCase.bw != null) {
//            TestCase.bw.close();
//        }
//    }
//
//    private void getNames() throws IOException {
//
//        ImmutableList.Builder<String> builder = new ImmutableList.Builder<String>();
//
//        if (this.hasHeader) {
//
//            for (String s : this.nextLine.split(this.delimiter)) {
//                builder.add(s);
//            }
//            this.nextLine = this.br.readLine();
//
//        } else {
//
//            for (int i = 0; i < this.numberOfColumns; i++) {
//
//                builder.add(this.fileName + ":" + i);
//            }
//        }
//        this.names = builder.build();
//
//    }
//
//    private void calcNumbers() {
//
//        this.numberOfColumns = this.nextLine.split(this.delimiter).length;
//    }

    @Override
    public ImmutableList<String> columnNames() {

        return null;
    }

    @Override
    public boolean hasNext() throws InputIterationException {

        return (this.nextLine != null);
    }

    @Override
    public ImmutableList<String> next() throws InputIterationException {

        if (this.hasNext()) {
            ImmutableList<String> result = this.getList(this.nextLine);
            try {
                this.nextLine = this.br.readLine();
            } catch (IOException e) {
                this.nextLine = null;
            }
            return result;
        } else {
            throw new InputIterationException("nix mehr da");
        }
    }

    private ImmutableList<String> getList(String nextLine2) {

        String[] splitted = this.nextLine.split(",");

        ImmutableList.Builder<String> builder = new ImmutableList.Builder<String>();

        for (String aSplitted : splitted) {

            String t = aSplitted;
            if (t == "") {
                t = null;
            } else {
                t = t.replaceAll("\"", "");
            }

            builder.add(t);
        }

        return builder.build();
    }

    @Override
    public int numberOfColumns() {

        return this.numberOfColumns;
    }

    @Override
    public String relationName() {

        return this.relationName;
    }

    @Override
    public RelationalInput generateNewCopy() throws InputGenerationException {

        return this;
    }

    @Override
    public void receiveResult(FunctionalDependency fd) throws CouldNotReceiveResultException {

        System.out.println(fd.getDeterminant() + "-->" + fd.getDependant());
        this.fds.add(fd);
    }

    @Override
    public Boolean acceptedResult(FunctionalDependency arg0) {
        return new Boolean(true);
    }

    public List<FunctionalDependency> getFds(){
        return this.fds;
    }
}
