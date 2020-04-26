import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The idea is to allow to transfer not only textual data
 * (it needs to have some modification in the future - i.e.: allowing .png files to be passed)
 */
public class  MultiMediaMessage extends Message{

    /**
     * Enum used to specify how safe does the message look like
     * if we find a "porn" substring, we can estimate it as dangerous, therefore we will use Safety.LOW
     * if we find a "http" substring, we can estimate it as not-too-safe, therefore we will use Safety.MEDIUM
     * if we do not find "any" suspicious substrings, we will estimate safety to HIGH
     */
    public enum Safety {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * No need for any extra fields, we can use parent's constructor
     * @param emailSender       author of the message
     * @param emailReceivers    receivers of the message
     * @param text              content of the message
     */
    public  MultiMediaMessage(String emailSender, List<String> emailReceivers, String text) {
        super(emailSender, emailReceivers, text);
    }


    /**
     * Combines metadata and content of a message to a single string and lists down all the links, that appeared
     * it does not really serve any particular purpose - it finds all (simple) links in the text message
     * and appends the original getDetails() output with a list of found links
     * At the end it will also check for malicious substrings and estimate how safe does the message tends to be
     * @return
     * Returns the combined string that describes all the data included in the message
     */
    @Override
    public String getDetails() {
        String details = super.getDetails();
        Safety sec_measure;
        if(this.getText().contains("http")){
            details += "\nlinks provided in above message:\n";

            Pattern pattern = Pattern.compile("(http|https)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(\\.com)");
            Matcher matcher = pattern.matcher(this.getText());
            while (matcher.find()){
                details += matcher.group() + '\n';
            }
        }
        if(this.getText().contains("porn"))         { sec_measure = Safety.LOW; }
        else if (this.getText().contains("http:"))  { sec_measure = Safety.MEDIUM; }
        else                                        { sec_measure = Safety.HIGH; }
        switch (sec_measure){
            case LOW:
                details += "\nMessage's may be dangerous - be careful\n";
                break;
            case MEDIUM:
                details += "\nMessage's content is suspicious\n";
                break;
            default:
                details += "\nMessage's content does not seem fishy\n";
                break;
        }

        return details;
    }

    /**
     * Simple main() method for functionality verification
     * @param args arguments form the console
     */
    public static void main(String[] args){

        /*
        Message m = new MultiMediaMessage("hajto@o2.pl",
                Arrays.asList("zmija69@o2.pl"),
                "siemka\n widziales juz moj najnowszy filmik? Jezdze tam sobie autkiem" +
                        "po Lodzi i jest fajnie - choc chyuba nie powinienem pisac kierujac" +
                        " jednoczesnie\n e tam... co moze pojsc nie tak...\n o nie stary, " +
                        "najgorzej\n dobra bede pozniej - ogarnij co dzieje sie we wiadomosciach" +
                        "sprawdz: https://polska.com albo na http://poczta.com. ja sam bede pozniej, elo!");

        System.out.println(m.getDetails());

         */
    }

}
