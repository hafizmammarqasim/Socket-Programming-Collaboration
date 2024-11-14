import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {
    private String messageId;
    private String senderName;
    private String message;
    private String receiverName;
    private int receiverId;
    private String reply;
    private static int idCounter = 1;
    private int senderId;
    String WaqtKiPabandi;

    public Message(int senderId, String senderName, String message, int receiverId, String reply, String receiverName,
            String WaqtKiPabandi) {
        this.messageId = String.format("%03d", idCounter++);
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.reply = reply;
        this.receiverName = receiverName;
        this.receiverId = receiverId;
        this.WaqtKiPabandi = WaqtKiPabandi;
    }

    public Message(Message sentMsg) {
        this.messageId = String.format("%03d", idCounter++);
        this.senderId = sentMsg.getSenderId();
        this.senderName = sentMsg.getSenderName();
        this.message = sentMsg.getMessage();
        this.reply = sentMsg.getReply();
        this.receiverName = sentMsg.getReceiverName();
        this.receiverId = sentMsg.getReceiverId();
        this.WaqtKiPabandi = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getSenderName() {
        return senderName;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "-------------Message-------------\n" +
                "MessageId        =   " + "(" + messageId + ")" + '\n' +
                "Sender Name      =   " + senderName + '\n' +
                "Sent Message     =   " + message + '\n' +
                "ReceiverName     =   " + receiverName + '\n' +
                "ReceiverId       =   " + receiverId + '\n' +
                "Received Message =   " + reply + '\n' +
                "Waqt             =   " + WaqtKiPabandi;
    }
}