

public class RegularUser extends User {

    public RegularUser(String firstName, String lastName, int month, int day, int year, String email, String password) {
        super(firstName, lastName, month, day, year, email, password);
    }

    public RegularUser(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

}
