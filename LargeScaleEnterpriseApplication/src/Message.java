import java.time.LocalDate;
import java.util.Date;
import java.util.List;


 // to keep once written message cannot be modified
public abstract class Message {

    final private String emailSender;
    final private List<String> emailReceivers;
    final private String text;
    final private Date date;
     private Boolean deleted;



    protected Message(String emailSender, List<String> emailReceivers, String text) {
        this.emailSender = emailSender;
        this.emailReceivers = emailReceivers;
        this.text = text;
        this.deleted = false;
        LocalDate now = LocalDate.now();
        this.date = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
    }

    // allows to delete (make unreadable) message if the request is sent by its author or an admin
    public void deleteMessage(User c){
        if(this.emailSender.equals(c.getEmail()) || c instanceof Administrator) {
            this.deleted = true;
        }
    }

     // --------------------
     // Getters and setters:

     public String getEmailSender() {
         return emailSender;
     }

     public List<String> getEmailReceivers() {
         return emailReceivers;
     }

     public String getText() {
         return text;
     }

     public Date getDate() {
         return date;
     }

     public Boolean getDeleted() {
         return deleted;
     }

     public String getDetails(){
         String details = "\nsender: " + this.getEmailSender() + '\n';

         // if there is only one receiver, we add a singular - else a plural
         details += getEmailReceivers().size() == 1 ? "receiver" : "receivers";
         for (String receiver : this.getEmailReceivers()) { details += '\n' + receiver; }
         details += "message content:\n" + this.getText();

         return details;
     };
}

