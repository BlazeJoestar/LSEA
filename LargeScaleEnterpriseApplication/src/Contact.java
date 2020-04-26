import java.io.*;

/**
 * Equivalent to a business card - it will store some basic data about other users
 * It is a basic element of a "phone book" field in User
 */
public final class Contact implements Comparable<Contact>, Serializable {

    /** Stores the real value of different user's first name */
    private String firstName;
    /** Stores the real value of different user's last name */
    private String lastName;
    /** Alias is a string that can keep a custom name prescribed to different user */
    private String alias;
    /** Stores email address of the different user */
    private String email;

    /**
     * Simple constructor
     * (by default we prescribe first name to alias)
     * @param firstName copy of the different user's first name
     * @param lastName  copy of the different user's last name
     * @param email     copy of the different user's email address
     */
    public Contact(String firstName, String lastName, String email){
        this.alias = firstName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Constructor that takes all respective Strings as parameters
     * @param alias     local alias for owner of an email
     * @param firstName copy of the different user's first name
     * @param lastName  copy of the different user's last name
     * @param email     copy of the different user's email address
     */
    public Contact(String alias, String firstName, String lastName, String email){
        this.alias = alias;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Implementation of 'Comparable' interface - allows to sort Contacts
     * using length of the last name as criterion
     * @param o object to be compared to the current one
     * @return  returns -1, 0 or 1 if parameter's last name is longer then,
     * equal to or shorter than last name field in current object respectively
     */
    @Override
    public int compareTo(Contact o) {
        if(o.getFirstName().length() == this.getFirstName().length()){
            return 0;
        }
        else if(o.getFirstName().length() > this.getFirstName().length()){
            return -1;
        }
        else {
            return 1;
        }
    }

    /**
     * Method allowing to save current state of the object
     * into a new text file
     * @param fileName  name of newly created file
     */
    public void saveToText(String fileName){
        String name = "saved-text-files\\" + fileName + ".txt";
        try (PrintWriter writer = new PrintWriter(new File(name))){
            writer.println(alias);
            writer.println(firstName);
            writer.println(lastName);
            writer.println(email);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overloaded method allowing to save current state of the object
     * into a new text file
     * @param fileName  string storing provided name
     * @param dir       string storing provided directory
     */
    public void saveToText(String fileName, String dir){
        String name = dir + fileName + ".txt";
        try (PrintWriter writer = new PrintWriter(new File(name))){
            writer.println(alias);
            writer.println(firstName);
            writer.println(lastName);
            writer.println(email);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method allowing to save current state of the object
     * into a new binary file
     * @param fileName  name of newly created file
     */
    public void saveToBinary(String fileName){
        String name = "saved-binary-files\\" + fileName + ".bin";
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(name))){
            os.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overloaded method that allows to save current state of the object
     * into a new binary file
     * @param fileName  string storing name of the file
     * @param dir       string storing provided directory
     */
    public void saveToBinary(String fileName, String dir){
        String name = dir + fileName + ".bin";
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(name))){
            os.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overriding the toString() method allows to create a simple string,
     * that shows content of each field in the object
     * @return  returns a formatted string that presents the actual state
     */
    @Override
    public String toString() {
        return  "\nemail address: " + email +
                "\nfull name: " + firstName + " " + lastName +
                "\naka: " + alias;
    }

    // getters & setters:

    /** @return returns "firstName" field content */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets "firstName" field value
     * @param firstName  string representation of the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** @return returns "lastName" field content */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets "lastName" field value
     * @param lastName  string representation of the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** @return returns "alias" field content */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets "alias" field value
     * @param alias  string representation of the alias - initially storing first name
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /** @return returns "email" field content */
    public String getEmail() {
        return email;
    }

    /**
     * Sets "email" field value
     * @param email  string representation of the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }


}