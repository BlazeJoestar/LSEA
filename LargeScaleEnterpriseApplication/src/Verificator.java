import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Scanner;

/**
 * A temporary tool for checking if everything works as expected
 */
public class Verificator {
    /**
     * main() method used for controlling purposes
     * @param args  list of arguments passed via terminal
     */
    public static void main(String[] args) throws CloneNotSupportedException {

        String data[] = new String[3];
        data[0] = "blazej";
        data[1] = "cwiklinski";
        data[2] = "zmija69@o2.pl";
        Contact c = new Contact( data[0], data[1], data[2] );

        ContactBinaryWriter binWriter       = new ContactBinaryWriter();
        ContactTextWriter   textWriter      = new ContactTextWriter();
        ContactBinaryReader binaryReader    = new ContactBinaryReader();
        ContactTextReader   textReader      = new ContactTextReader();

        binWriter.writeBinary("temp", c);
        textWriter.writeText("temp", c);

        Contact copy_1 = binaryReader.readBinary("temp");
        Contact copy_2 = textReader.readText("temp");

        System.out.println(copy_1);
        System.out.println(copy_2);

        /*
        // -----------------------------------------------------------
        // ----------------- USER HANDLING DEMO ----------------------

        // Creation of necessary variables
        Scanner sc = new Scanner(System.in);
        String name = null, choice = null;
        String data[] = new String[3];
        boolean run = true;

        System.out.println("Hello, I will create a contact:\n please provide " +
                "a name, a surname and en email address:");

        // getting name, surname and email from the user
        for(int i = 0; i < 3; i++){
            data[i] = sc.nextLine();
        }
        // creating such object
        Contact c = new Contact( data[0], data[1], data[2] );

        System.out.println("Newly created object:\n" + c + "\n");

        // asking for type of format and a name of saved file
        while(run){
            System.out.println("Please choose type of saving - type:\n\"b\" to save in binary format " +
                    "\n\"t\" to save in text format");
            choice = sc.nextLine();
            switch (choice){
                case "b": // save to binary + serialization
                    System.out.println("provide name of a the saved binary file");
                    name = sc.nextLine();
                    c.saveToBinary(name);
                    run = false;
                    break;
                case "t": // save to text file
                    System.out.println("provide name of a the saved text file");
                    name = sc.nextLine();
                    c.saveToText(name);
                    run = false;
                    break;
            }
        }

        // verifying if the above block of code really works as expected:
        switch (choice){
            case "b":
                // read from the binary
                ContactBinaryReader c_bin_reader = new ContactBinaryReader();
                Contact c_bin_copy = c_bin_reader.readBinary(name);

                System.out.println(c_bin_copy);
                break;
            case "t":
                // read from the text file
                ContactTextReader c_text_reader = new ContactTextReader();
                Contact c_text_copy = c_text_reader.readText(name);

                System.out.println(c_text_copy);
                break;
        }
        // -----------------------------------------------------------
        */
        /*
        // number of messages to be taken to the program
        int numberOfMessages = 100000;
        int min_threads_convenient = 1;
        int max_threads_convenient = 8;

        // "convenient" - number of logical threads
        for (int i = min_threads_convenient; i <= max_threads_convenient; i++){
            Statistic stat = new Statistic(i, numberOfMessages);
            stat.performCalculations();
        }

        System.out.println();

        // "inconvenient" - big random number
        int min_threads_inconvenient = 16;
        int max_threads_inconvenient = 128;
        for (int i = min_threads_inconvenient; i <= max_threads_inconvenient; i+=min_threads_inconvenient){
            Statistic stat = new Statistic(i, numberOfMessages);
            stat.performCalculations();
        }
        */
        /*
        // ############ COMPARATOR:
        User user = new RegularUser("Blazej", "Cwiklinski", "zmija@o2.pl", "Lodz_2007");
        List<Contact> list = new ArrayList<Contact>();
        list.add(new Contact("aaaaaa", "x", "z"));
        list.add(new Contact("bbbb", "x", "z"));
        list.add(new Contact("ab", "x", "z"));
        user.setPhoneBook(list);
        user.sortPhoneBook();
        for (Contact c : user.getPhoneBook()){
            System.out.println(c.getFirstName());
        }

        System.out.println("\n\n");

        // ############ COMPARABLE:
        Collections.sort(list);
        for (Contact c : list){
            System.out.println(c.getFirstName());
        }

        System.out.println("\n\n");

        // ############ CLONE:
        // c1_deep jest osobna instancja, zmiana c1 nie bedzie afaktowala c1_deep,
        // z kolei stan c1_shallow zmieni sie, gdyz wskazuje on (c1_shallow) na ten sam obiekt co c1
        Contact c1 = new Contact("Blazej", "Cwiklinski","zmija69@o2.pl");
        Contact c1_shallow = c1;
        Contact c1_deep = (Contact) c1.clone();
        System.out.printf("orginal: %s\nshallow: %s\ndeep: %s\n\n",
                c1.getFirstName(), c1_shallow.getFirstName(), c1_deep.getFirstName());
        c1.setFirstName("Tomasz");
        System.out.printf("orginal: %s\nshallow: %s\ndeep: %s\n\n",
                c1.getFirstName(), c1_shallow.getFirstName(), c1_deep.getFirstName());


        List<String> receivers = new ArrayList<String>();
        receivers.add("a@wp.pl");
        receivers.add("b@wp.pl");
        receivers.add("c@wp.pl");

        TextMessage t1 = new TextMessage("xxx@wp.pl", receivers, "Hello y'all");

        TextMessage t2 = null;
        try {
            t2 = (TextMessage) t1.clone();
            System.out.println("\nClonned!\n");
        } catch (CloneNotSupportedException e) {
            e.getMessage();
        }
        // Wypisujemy liste oryginalna i sklonowana
        System.out.println("\nOriginal:");
        for (String t1_receivers : t1.getEmailReceivers()){
            System.out.println(t1_receivers);
        }
        System.out.println("\nClonned one:");
        for (String t2_receivers : t2.getEmailReceivers()){
            if(t2_receivers == null) {
                System.out.println("\nthe list is empty!\n");
            }
            System.out.println(t2_receivers);
        }

        System.out.println("\n\n");

        // a nastepnie wprowadzamy zmiany do oryginalnej
        List<String> new_receivers = new ArrayList<String>();
        receivers.add("x@wp.pl");
        receivers.add("y@wp.pl");
        receivers.add("z@wp.pl");
        t1.updateReceivers(new_receivers);

        // i sprawdzamy, czy rzeczywiscie dziala jak dzialac powinno
        System.out.println("\nOriginal:");
        for (String t1_receivers : t1.getEmailReceivers()){
            System.out.println(t1_receivers);
        }
        System.out.println("\nClonned one:");
        for (String t2_receivers : t2.getEmailReceivers()){
            System.out.println(t2_receivers);
        }


        // we show our polymorphism here
        List<User> allUsers = new ArrayList<User>();
        User u1 = new RegularUser("Tomasz", "Hajto", "baba@o2.pl", "Lodz_2007");
        User u2 = new Administrator("Blazej", "Cwiklinski", 9, 22, 1998, "zmija69@o2.pl", "viper");
        // adding new users to the allUsers list
        allUsers.add(u1);
        allUsers.add(u2);
        // proof that User class is abstract
        // User u3 = new User("ajcbd", "ajadc", "asd", " ahbd");
        for (User u : allUsers){
            System.out.println(u);
        }
        */
    }

    /**
     *  Simple static class that allows us to read a text file and create
     *  a new Contact object using the data stored in mentioned text file
     */
    static class ContactTextReader {
        /**
         * Reads the .txt file and creates a new Contact object using
         * data provided in the text file
         * @param fileName  name of a the file we want to read from
         * @return          new Contact object - data from the .txt file
         */
        public Contact readText(String fileName){
            String name = "saved-text-files\\" + fileName + ".txt";
            String arr[] = new String[4];

            try (Scanner scanner = new Scanner(new File(name))){
                int it = 0;
                while (scanner.hasNext()){
                    arr[it++] = scanner.nextLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Contact c = new Contact(arr[0], arr[1], arr[2], arr[3]);
            return c;
        }
    }

    /**
     *  Simple static class that allows us to create a text file
     *  using data provided by passed Contact instance
     */
    static class ContactTextWriter {
        /**
         * Creates the .txt file using passed Contact object
         * @param fileName  name of a the file we want to write to
         * @param contact   object we want to take data from for the txt
         */
        public void writeText(String fileName, Contact contact){
            String name = "saved-text-files\\" + fileName + ".txt";
            try (PrintWriter writer = new PrintWriter(new File(name))){
                writer.println(contact.getAlias());
                writer.println(contact.getFirstName());
                writer.println(contact.getLastName());
                writer.println(contact.getEmail());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Simple static class that allows us to read a binary file and create
     * a new Contact object using the data stored in mentioned binary file
     */
    static class ContactBinaryReader {
        /**
         *  Reads the .bin file and creates a new Contact object using
         *  data provided in the binary file
         * @param fileName  name of a the file we want to read from
         * @return          new Contact object - data from the binary file
         */
        public Contact readBinary(String fileName) {
            String name = "saved-binary-files\\" + fileName + ".bin";

            Contact c = null;
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(name))) {
                c = (Contact) is.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return c;
        }
    }

    /// -----------------------------------------------
    /// below code needs improvements and is unfinished
    /**
     * Simple static class that allows us to write into a binary file
     * using instance of Contact class
     */
    static class ContactBinaryWriter {
        static FileChannel fileChannel = null;
        static RandomAccessFile randomAccessFile = null;
        /**
         * Method allowing to save state of the passed object
         * into a new binary file
         * @param fileName  name of newly created file
         * @param contact   file we want to copy into binary
         */
        public void writeBinary(String fileName, Contact contact) {
            String name = "saved-binary-files\\" + fileName + ".bin";
            try {
                randomAccessFile = new RandomAccessFile(name, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            fileChannel = randomAccessFile.getChannel();
            try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(name));
                var fileLock = fileChannel.tryLock()){
                if(fileLock != null){
                    os.writeObject(contact);
                    //Thread.sleep(10000);
                    fileLock.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (OverlappingFileLockException e) {
                e.printStackTrace();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            }finally{

                //fileChannel.close();
                //randomAccessFile.close();

            }
        }
    }
}