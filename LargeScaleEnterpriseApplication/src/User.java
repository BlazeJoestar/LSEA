import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public abstract class User {

    // --------------------------------------------------
    // since email has to be unique it will substitute ID
    private String email;
    private String password;

    // only way to set it is via login()/logout() methods
    private Boolean active;


    private String firstName;
    private String lastName;
    private Date birth;
    private List<Contact> phoneBook;
    private List<Message> messageHistory;

    // -----------------------------------------------------
    // Full constructor used when someone creates an account
    public User(String firstName, String lastName, int month, int day, int year, String email, String password){
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.birth =  new Date(year, month, day);
            this.password = password;
            this.active = false;
    }

    // ---------------------------------------------
    // "shorter" version of constructor when someone
    // does not want to share his/her actual birth date
    // but app asks if the potential user is adult, and
    // after confirmation sets age at precisely 18 years old.
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


    public void login(String s){
        if(this.getPassword().equals(s)){
            this.setActive(true);
        }
    }

    public void logout(){
        this.setActive(false);
    }

    // eventually it will work with database and is going to
    // take an email address, then request database for the
    // business card of client whose id is equal to mentioned email
    public void addContact(Contact businessCard){
        this.phoneBook.add(businessCard);
    }

    @Override
    public String toString() {
        return "\nUser: " + this.getFirstName() + " " + this.getLastName();
    }

    // --------------------
    // Getters and setters:

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contact> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(List<Contact> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // --------------------------------
    // main() used for testing purposes

    public static void main(){

    }


}
