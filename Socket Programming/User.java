import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class User {
    int contactId;
    private static int flagForMessage = 0;
    Message[] messages = new Message[5];
    String senderName;
    String message;
    String receiverName;
    int receiverId;
    String reply;
    String contactName;

    Scanner myInput = new Scanner(System.in);

    public User(int contactId, String contactName) {
        this.contactId = contactId;
        this.senderName = contactName;
    }

    public User(int contactId, Message[] messages, String contactName) {
        this.contactId = contactId;
        this.messages = messages;
        this.contactName = contactName;
    }

    public int getPersonId() {
        return contactId;
    }

    public void setPersonId(int contactId) {
        this.contactId = contactId;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public String getContactName() {
        return senderName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Message sendMessage() {
        String WaqtKiPabandi = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < messages.length; i++) {
            if (messages[i] == null) {
                System.out.println("Enter Receiver's Name: ");
                receiverName = myInput.nextLine();

                System.out.println("Enter Receiver's Id");
                receiverId = myInput.nextInt();
                myInput.nextLine();

                System.out.println("Enter your Message: ");
                message = myInput.nextLine();

                messages[i] = new Message(contactId, senderName, message, receiverId, null, receiverName,
                        WaqtKiPabandi);
                System.out.println("Message Has Been Sent");
                return messages[i];
            }
        }
        return null;
    }

    public void receiveMessage(Message sentmsg) {
        // code for accepting reply
        if (sentmsg.getMessage() != null && sentmsg.getReply() != null) {
            for (int i = 0; i <= messages.length; i++) {
                if (messages[i] != null && messages[i].getMessage().equals(sentmsg.getReply())) {
                    String dummyMsgId = messages[i].getMessageId();
                    messages[i].setMessageId(dummyMsgId);
                    messages[i].setMessage(sentmsg.getReply());
                    messages[i].setReply(sentmsg.getMessage());
                    System.out.println(messages[i]);
                    break;
                }
            }
        } else if ((sentmsg.getMessage() != null && sentmsg.getReply() == null)) {
            for (int i = 0; i <= messages.length; i++) {
                if (messages[i] == null) {
                    messages[i] = new Message(sentmsg);
                    messages[i].setSenderId(sentmsg.getReceiverId());
                    messages[i].setReceiverId(sentmsg.getSenderId());
                    messages[i].setReply(sentmsg.getMessage());
                    messages[i].setMessage(sentmsg.getReply());
                    System.out.println(messages[i]);
                    return;
                }
            }
            System.out.println("Message cannot be added");
        }
    }

    public Message replyMessage() {
        int counter = 0;

        for (Message msg : messages) {
            if (msg != null) {
                if (msg.getReply() != null && msg.getMessage() == null) {
                    if (counter == 0) {
                        System.out.println("--------------------------");
                        System.out.println("--Un Replied Messages");
                        System.out.println("--------------------------");
                    }
                    System.out.println(msg);
                    System.out.println("-----------------------------------");
                    counter++;
                }
            }
        }

        if (counter > 0) {
            flagForMessage = 0;
            System.out.println("Enter the msg id to reply to");
            String replyId = myInput.nextLine();

            for (int i = 0; i < messages.length; i++) {
                if (messages[i] != null && replyId.equalsIgnoreCase(messages[i].getMessageId())) {
                    flagForMessage = i;
                    break;
                }
            }
            System.out.println("Enter the reply:");
            reply = myInput.nextLine();
            messages[flagForMessage].setMessage(reply);
            System.out.println("Reply has been sent");

            return messages[flagForMessage];

        } else if (messages[flagForMessage] == null
                || messages[flagForMessage].getReply() == null && messages[flagForMessage].getMessage() == null) {
            System.out.println("You haven't got any messages yet");
        }
        return null;

    }

    public void displayAllMessages() {
        for (Message msg : messages) {
            System.out.println("-------------------------");
            if (msg != null) {
                System.out.println(msg);
                System.out.println("----------------------");
            }
        }
    }

    public void searchByContent(String msgContent) {
        for (Message value : messages) {
            if (value != null) {
                if (value.getMessage() != null) {
                    if (value.getMessage().contains(msgContent)) {
                        System.out.println(value);
                        return;
                    }
                }
            }
        }
    }

    public Message byMsgId() {
        System.out.println("--Searching by Msg Id--");
        System.out.println("Enter the msg Id");
        String msgId = myInput.nextLine();

        for (int i = 0; i < messages.length; i++) {
            if (messages[i] != null && msgId.equalsIgnoreCase(messages[i].getMessageId())) {
                flagForMessage = i;
                return messages[i];
            }
        }
        return null;
    }

    public void display() {
        Message msg = byMsgId();
        if (msg != null) {
            System.out.println(msg);
        }
    }

    public void deleteMessages() {
        flagForMessage = 0;
        byMsgId();
        if (flagForMessage != 0) {
            messages[flagForMessage] = null;
            System.out.println("Message deleted Successfully");
        }
    }

}