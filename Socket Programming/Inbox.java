import java.util.Scanner;

public class Inbox {
    int personId;
    User[] user = new User[5];
    private static int flagForDelete = -1;

    Scanner myInput = new Scanner(System.in);

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public User[] getUser() {
        return user;
    }

    public void sendNewMessage(User[] user) {
        this.user = user;
    }

    public int searchFunction() {
        System.out.println("Enter Sender's(Your) Contact Id");
        personId = myInput.nextInt();
        myInput.nextLine();
        return personId - 1;

    }

    public Message addUser() {
        System.out.println("--Sending Message--");
        int flag = searchFunction();
        System.out.println("Enter Sender's(Your) Name");
        String contactName = myInput.nextLine();
        if (user[flag] == null) {
            user[flag] = new User(flag + 1, contactName);
            return user[flag].sendMessage();
        } else
            return user[flag].sendMessage();
    }

    public void receiveMessage(Message sentmsg) {
        int index = sentmsg.getReceiverId();
        if (user[index - 1] == null) {
            user[index - 1] = new User(index, sentmsg.getReceiverName());
            System.out.println("new contact created");
        }
        user[index - 1].receiveMessage(sentmsg);
    }

    public Message replyMessage() {
        int flag = searchFunction();
        if (user[flag] == null) {
            System.out.println("Msg does not exist");
            return null;
        }
        return user[flag].replyMessage();
    }

    public void displayAllMessages() {
        for (User u : user) {
            if (u != null)
                u.displayAllMessages();
        }
    }

    public void deleteMessage() {
        System.out.println("--CHOOSE--");
        System.out.println("1. Delete Whole Chat");
        System.out.println("2. Delete Specific Message");
        System.out.println("0. Return to Main Menu");
        int choice = myInput.nextInt();
        myInput.nextLine();

        switch (choice) {
            case 1:
                deleteWholeChat();
                break;

            case 2:
                deleteSpecificMessage();
                break;

            case 0:
                return;

            default:
                System.out.println("Invalid Input");
                deleteMessage();
                break;
        }
    }

    public void deleteWholeChat() {
        byName();
        if (flagForDelete != -1) {
            user[flagForDelete] = null;
            System.out.println("Chat is deleted Successfully");
        }
    }

    public void deleteSpecificMessage() {
        byName().deleteMessages();
    }

    public void specificContactMsgs() {
        System.out.println("--Messages of Specific contact--");
        System.out.println("1. By Name");
        System.out.println("2. By Contact Id");
        System.out.println("0. Return to main Menu");
        int choice = myInput.nextInt();
        myInput.nextLine();

        switch (choice) {
            case 1:
                User returnOfName = byName();
                if (returnOfName != null)
                    returnOfName.displayAllMessages();
                break;

            case 2:
                User returnOfId = byContactId();
                if (returnOfId != null)
                    returnOfId.displayAllMessages();
                break;

            case 0:
                return;

            default:
                System.out.println("Invalid Input");
                specificContactMsgs();
                break;
        }
    }

    public void choiceForSearch() {
        System.out.println("Choose the option:");
        System.out.println("1. By name");
        System.out.println("2. By by Id");
        System.out.println("3. By Message Content");
        int choice = myInput.nextInt();
        myInput.nextLine();

        switch (choice) {
            case 1:
                byName().display();
                break;

            case 2:
                byContactId().display();
                break;

            case 3:
                byContent();
                break;

            default:
                System.out.println("Invalid Input");
                choiceForSearch();
                break;
        }
    }

    public User byName() {
        flagForDelete = 0;
        System.out.println("Enter the name:");
        String name = myInput.nextLine();

        for (int i = 0; i < user.length; i++) {
            if (user[i] != null && user[i].getContactName().equalsIgnoreCase(name)) {
                flagForDelete = i;
                // System.out.println("flag for delete"+flagForDelete);
                return user[i];
            }
        }
        return null;
    }

    public User byContactId() {
        System.out.println("Enter the contact Id");
        int id = myInput.nextInt();

        return user[id - 1];
    }

    public void byContent() {
        System.out.println("Enter the msg content");
        String msgContent = myInput.nextLine();

        for (User u : user) {
            if (u != null) {
                u.searchByContent(msgContent);
            }
        }
    }

}