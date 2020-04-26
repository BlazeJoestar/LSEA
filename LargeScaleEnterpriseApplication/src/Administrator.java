
/**
 * A kind of "SuperUser" - instance will be able to delete messages of regular users
 * and  have some extra features in future
 */
public class Administrator extends User {

    /**
     * Basic constructor for Administrator class
     * @param firstName first name of administrator
     * @param lastName  last name of administrator
     * @param month     month of birth
     * @param day       day of birth
     * @param year      year of birth
     * @param email     email used as a verifier for potential administrator
     * @param password  password that the user will be asked for when logging in
     */
    public Administrator(String firstName, String lastName, int month, int day, int year, String email, String password) {
        super(firstName, lastName, month, day, year, email, password);
    }

    /**
     * Introduction of the user
     * (nothing fancy, just need to override something - we change output from "User: " to  "Administrator: ")
     * @return
     * method returns a string that declares position (in that case - administrator) and full name of th user
     */
    @Override
    public String toString() {
        return "Admininstrator: " + super.toString();
    }
}