import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.stream.Collectors;

/**
 * If a message will consist only of text (no .png files and so on) it will be an instance of TextMessage class
 * because of simple structure, it will allow to be driven on "lightweight" version of our application
 */
public class TextMessage extends Message implements Cloneable {
    public TextMessage(String emailSender, List<String> emailReceivers, String text) {
        super(emailSender, emailReceivers, text);
    }


    // < EX 1  --------------------------------------------------------------------------------------------------

    /**
     * Implementation of 'Cloneable' interface - will create entirely new object with no common references
     * @return returns new object that is the same as, but independent from the original object
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        TextMessage message;
        //try {
        message = new TextMessage(
                this.getEmailSender(),
                // could go for java 8 (and above), but let's keep it simple
                //this.getEmailReceivers().stream().map(String::new).collect(Collectors.toList()),
                new ArrayList<String>(),
                this.getText());
        for(String s : this.getEmailReceivers()){
            message.addSingleReceiver(s);
        }
        return message;
            //message = (TextMessage) super.clone(); // <=== made me crazy
            //System.out.println("hello, hope you're having fun");
        /*
        } catch (CloneNotSupportedException e) {
            System.out.printf("%s\n", e.getMessage());
            message = new TextMessage(
                    this.getEmailSender(),
                    this.getEmailReceivers().stream().map(String::new).collect(Collectors.toList()),
                    //new ArrayList<String>(),
                    this.getText());
            //for(String s : this.getEmailReceivers()){
            //    message.addSingleReceiver(new String(s));
            //}
        }
        return message;

         */
    }
    // EX 1 ?> --------------------------------------------------------------------------------------------------


}
