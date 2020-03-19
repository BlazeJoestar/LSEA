import java.util.*;

public class Verificator {
    public static void main(String[] args){
        List<User> allUsers = new ArrayList<User>();

        User u1 = new RegularUser("Tomasz", "Hajto", "stara_baba@o2.pl", "Lodz_2007");
        User u2 = new Administrator("Blazej", "Cwiklinski", 9, 22, 1998, "zmija69@o2.pl", "viper");

        
        allUsers.add(u1);
        allUsers.add(u2);

        for (User u : allUsers){
            System.out.println(u);
        }
    }
}
