import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  MultiMediaMessage extends Message{

    public  MultiMediaMessage(String emailSender, List<String> emailReceivers, String text) {
        super(emailSender, emailReceivers, text);
    }

    @Override
    public String getDetails() {
        String details = super.getDetails();

        if(this.getText().contains("http")){
            details += "\nlinks provided in above message:\n";

            Pattern pattern = Pattern.compile("(http)(.*)(.com)");
            Matcher matcher = pattern.matcher(this.getText());
            while (matcher.find()){
                details += matcher.group() + '\n';
            }
        }

        return details;
    }
}
