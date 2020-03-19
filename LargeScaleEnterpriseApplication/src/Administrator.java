

public class Administrator extends User {


    public Administrator(String firstName, String lastName, int month, int day, int year, String email, String password) {
        super(firstName, lastName, month, day, year, email, password);
    }

    @Override
    public String toString() {
        return "Admininstrator: " + this.getFirstName() + " " + this.getLastName();
    }
}
