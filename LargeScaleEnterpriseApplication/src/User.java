import java.time.LocalDate;
import java.util.*;

/**
 * Basic class describing common methods and fields that cannot be instantiated - all users
 * have to be declared either as administrators or as regular users
 */
public abstract class User {

    /**
     * Simple implementation of Comparator interface allowing
     * to sort a list of Contacts by the firstName field
     */
    class ByName implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            return (o1.getFirstName().compareTo(o2.getFirstName()));
        }
    }

    /**
     * Allows to use nested class ByName to sort phoneBook by firstName field
     */
    public void sortPhoneBook(){
        Collections.sort(this.phoneBook, new ByName());
    }

    /** Stores email address of the user - it will be used as an ID, i.e. has to be unique */
    private String email;
    /** Stores password of the user */
    private String password;
    /** Defines whether someone a given account right now
     * it can be changed only by setting it is with login()/logout() methods */
    private Boolean active;
    /** Stores first name of the user */
    private String firstName;
    /** Stores last name of the user */
    private String lastName;
    /** Stores birth date of the user */
    private Date birth;
    /** Stores a list of contacts (other users' email addresses, etc.) of the user */
    private List<Contact> phoneBook;
    /** Stores a list of messages the user has access to
     *  <it will be changed to list of message keys - it will be impossible to keep all the objects assigned
     *  (or even shared) to specific users
     */
    private List<Message> messageHistory;

    /**
     *  A complete constructor used when someone creates an account
     * @param firstName first name of the user
     * @param lastName  last name of the user
     * @param month     month of birth
     * @param day       day of birth
     * @param year      year of birth
     * @param email     email used as a verifier for potential user
     * @param password  password that the user will be asked for when logging in
     */
    public User(String firstName, String lastName, int month, int day, int year, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birth =  new Date(year, month, day);
        this.password = password;
        this.active = false;
    }

    /**
     * Less-complete option of account creation - assuming that the user is at least 18 years old
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param email     user's email address    - uniqueness
     * @param password  user's password         - confirmation
     */
    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        LocalDate now = LocalDate.now();
        now = now.minusYears(18);
        this.birth = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        this.password = password;
        this.active = false;
    }

    /**
     * eventually it will work with database and is going to
     * take an email address, then request database for the
     * business card of client whose id is equal to mentioned email
     * @param businessCard  a way to keep contacts assigned to out account without copying the whole object
     */
    public void addContact(Contact businessCard){
        if(!this.phoneBook.contains(businessCard));
            this.phoneBook.add(businessCard);
    }

    /**
     * Introduction of the user
     * @return returns first and last name concatenated
     */
    @Override
    public String toString() {
        return (this.firstName + " " + this.lastName);
    }

    /**
     * Sets "active" field value to true if provided the right password
     * @param s a string storing an attempt to change the 'active' status to true
     */
    public void login(String s){
        if(this.getPassword().equals(s)){
            this.active = true;
        }
    }

    /** Sets 'active' status to false when called */
    public void logout(){
        this.active = false;
    }

    // getters & setters:

    /** @return returns user's password */
    private String getPassword() {
        return password;
    }

    /**
     * Sets "password" field value
     * @param password user's new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** @return returns user's first name */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets "firstName" field value
     * @param firstName user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** @return returns last name of the user */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets "lastName" field value
     * @param lastName user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** @return returns user's birth date */
    public Date getBirth() {
        return birth;
    }

    /**
     * Sets "birth" field value
     * @param birth specifies the day the user was born
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /** @return returns user's email address */
    public String getEmail() {
        return email;
    }

    /** @return returns content of user's phoneBook (a list of contacts)  */
    public List<Contact> getPhoneBook() {
        return phoneBook;
    }

    /**
     * Sets "phoneBook" field value
     * @param phoneBook a list of contacts the user wishes to store in the phone book
     */
    public void setPhoneBook(List<Contact> phoneBook) {
        this.phoneBook = phoneBook;
    }
}