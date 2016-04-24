package zakp.fireproject.model;


import java.util.List;

public class MessageChatModel {

    private String message;
    private String sender;
    private List<String> logins;

    private int mRecipientOrSenderStatus;


    /* Setter */

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecipientOrSenderStatus(int recipientOrSenderStatus) {
        this.mRecipientOrSenderStatus = recipientOrSenderStatus;
    }


    public void setSender(String givenSender){
        sender=givenSender;
    }


    /* Getter */

    public String getMessage() {
        return message;
    }

    public String getSender(){
        return sender;
    }

    public int getRecipientOrSenderStatus() {
        return mRecipientOrSenderStatus;
    }

    public List<String> getLogins() {
        return logins;
    }

    public void setLogins(List<String> logins) {
        this.logins = logins;
    }
}
