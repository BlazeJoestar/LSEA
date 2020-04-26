import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This object will generate some statistics for analysis purposes
 * it is going to load database's content, filter it, split it and dedicate its parts
 * to threads for calculations
 *
 */
public class Statistic {

    /** Stores the number of threads that will be brought to life */
    private int threads;
    /** Stores the number of messages to be interpreted by Statistic object */
    private int numberOfMessages;
    /** Stores the total length of all the messages */
    private long totalLength;
    /** Stores the average length of a single message */
    private double averageLength;
    /** Object that will allow us to read from a text file */
    private BufferedReader bufferedReader;
    /** Stores a list of Array Lists dedicated for each thread */
    private ArrayList<ArrayList<String>> allTheMessages;
    /** Stores objects extending the Thread class  */
    private List<Calculator> calculators;

    /**
     * Basic constructor that will set all the values either for default (e.g. there will be at least one thread)
     * It will also initialize lists and call "splitForThreads()" method
     * @param threads           number of threads brought to life
     * @param numberOfMessages  number of messages to be included in calculations
     */
    public Statistic(int threads, int numberOfMessages) {

        this.totalLength = 0;
        this.averageLength = 0.0;
        this.numberOfMessages = numberOfMessages;

        if (threads < 1){
            this.threads = 1;
        }
        else{
            this.threads = threads;
        }
        // we try to initialize our buffered reader
        try {
            bufferedReader = new BufferedReader(new FileReader("C:\\Program Files\\mess.txt"));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        // and we initialize our lists
        allTheMessages = new ArrayList<>();
        calculators = new ArrayList<>();

        // now we will split the data, so that all threads can work without interrupting one another
        splitForThreads();
    }

    /**
     * This method will split the big file into smaller (sub)lists dedicated for threads
     */
    private void splitForThreads() {
        int interval = numberOfMessages / threads;
        try {
            for (int i = 0; i < threads; i++) {
                ArrayList<String> temp = new ArrayList<>();
                for (int j = (i*interval); j < (i*interval+interval); j++) {
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    temp.add(bufferedReader.readLine());
                }
                allTheMessages.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Critical section needs to be protected - here is my guard for totalLength variable
     * @param results   number of words read in one line by a thread in a line
     */
    private synchronized void addMessageLength(long results){
        totalLength = totalLength + results;
    }

    /**
     * A method that will create all the threads and run them
     */
    public void performCalculations() {

        long start, stop;
        start = System.currentTimeMillis();
        for(ArrayList<String> arr : allTheMessages){
            Calculator c = new Calculator(arr);
            calculators.add(c);
        }
        for(Calculator c : calculators){
            c.run();
        }
        try {
            for(Calculator c : calculators){
                c.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop = System.currentTimeMillis();
        System.out.printf(
                "For %d thread%c %d calculations took: %d milliseconds\n",
                threads, (threads>1) ? 's' : ' ', (numberOfMessages/threads), (stop - start));

        // We print the result of our calculations
        averageLength = totalLength/numberOfMessages;
        System.out.println("Average length of a message = " + averageLength);
    }

    /**
     * A class that extends Thread class, and let us preform multiple operations parallelly
     */
    public class Calculator extends Thread{

        /** Stores the assigned list of strings to be calculated by a single thread */
        private final ArrayList<String> list;

        /**
         * Constructor that will assigned given list to a local variable
         * @param list  list of strings that will be used for calculations
         */
        public Calculator(ArrayList<String> list){
            this.list = list;
        }

        /** The method, that does the "magic part" :) */
        public void run(){
            for(String s : list)
                addMessageLength(s.split(" ").length);
        }
    }
}
