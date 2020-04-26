import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An abstract class working as a template for all messages - the idea is that for different versions of this app
 * (e.g.: "mobile", "mobile-lite", "desktop") there might be different types for available options
 * i.e. for "mobile-lite" it will be possible to use text messages only, and "mobile" will handle all the types.
 *
 * This class does not support "generic" setters, because once initialized a message can:
 *      -be deleted,
 *      -have receivers updated(added)
 */
public abstract class Message{

    /** Stores the email address of the sender */
    final private String emailSender;
    /** Stores the list of email addresses of the receivers */
    final private List<String> emailReceivers;
    /** Stores text content of a message */
    final private String text;
    /** Stores the date of message initialization */
    final private Date date;
    /** Determines whether the content of the message is available or not */
    private Boolean deleted;


    /**
     * A simple constructor with no overloading
     * @param emailSender       email address of the author
     * @param emailReceivers    email addresses of all the receivers
     * @param text              content of the message
     */
    protected Message(String emailSender, List<String> emailReceivers, String text) {
        this.emailSender = emailSender;
        this.emailReceivers = emailReceivers;
        this.text = text;
        this.deleted = false;
        LocalDate now = LocalDate.now();
        this.date = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
    }


    /**
     * Allows to delete (make unreadable) message if the request is sent by its author or an admin
     * @param c user requesting the deletion needs to pass her/his email to verify if she/he can do so
     */
    public void deleteMessage(User c){
        if(this.emailSender.equals(c.getEmail()) || c instanceof Administrator) {
            this.deleted = true;
        }
    }

    /**
     * Allows to update the list of receivers, avoiding the need to initialize a new message
     * @param newReceivers  stores a list of new receivers of the message
     */
    public void updateReceivers(List<String> newReceivers){
        for (String newOne : newReceivers){
            if(!this.emailReceivers.contains(newOne)){
                this.emailReceivers.add(newOne);
            }
        }
    }

    /**
     * Allows to update the list of receivers, avoiding the need to initialize a new message
     * @param newReceiver  stores a string representing a new receiver of the message
     */
    public void addSingleReceiver(String newReceiver) {
        if(!this.emailReceivers.contains(newReceiver)){
            this.emailReceivers.add(newReceiver);
        }
    }

    /**
     * Combines metadata and content of a message to a single string
     * @return
     * Returns the combined string that describes all the data included in the message
     */
    public String getDetails(){
        String details = "\nsender: " + this.getEmailSender() + '\n';

        // if there is only one receiver, we add a singular - else a plural
        details += getEmailReceivers().size() == 1 ? "receiver\n" : "receivers\n";
        for (String receiver : this.getEmailReceivers()) { details += '\n' + receiver; }
        details += "\nmessage content:\n" + this.getText();

        return details;
    }

    // getters:

    /** @return returns email of the sender */
    public String getEmailSender() {
        return emailSender;
    }

    /** @return returns a list of emails of receivers  */
    public List<String> getEmailReceivers() {
        return emailReceivers;
    }

    /** @return returns content of the message */
    public String getText() {
        return text;
    }

    /** @return returns date of message initialization */
    public Date getDate() {
        return date;
    }

    /** @return returns true when message was deleted, false if was not */
    public Boolean getDeleted() {
        return deleted;
    }
}