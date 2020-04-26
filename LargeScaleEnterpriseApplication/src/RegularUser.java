
/**
 * Representation of regular users profile in the app
 * Later on, the functionality will be extended to provide all the functionalities for non-admin accounts
 */
public class RegularUser extends User {

    /**
     * The "complete" option of account creation - providing all the necessary data + specific birth date
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param month     month of birth
     * @param day       day of birth
     * @param year      year of birth
     * @param email     user's email address    - uniqueness
     * @param password  user's password         - confirmation
     */
    public RegularUser(String firstName, String lastName, int month, int day, int year, String email, String password) {
        super(firstName, lastName, month, day, year, email, password);
    }

    /**
     * Less-complete option of account creation - assuming that the user is at least 18 years old
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param email     user's email address    - uniqueness
     * @param password  user's password         - confirmation
     */
    public RegularUser(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    /**
     * Introduction of the user
     * We simply add "User: " to a string returned by the super class
     * @return
     * method returns a string that declares position (in that case - regular user) and full name of th user
     */
    @Override
    public String toString() {
        return "Regular user: " + super.toString();
    }
}