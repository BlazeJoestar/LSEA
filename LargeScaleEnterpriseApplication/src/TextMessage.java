import java.util.List;

public class TextMessage extends Message{

    public TextMessage(String emailSender, List<String> emailReceivers, String text) {
        super(emailSender, emailReceivers, text);
    }

}

