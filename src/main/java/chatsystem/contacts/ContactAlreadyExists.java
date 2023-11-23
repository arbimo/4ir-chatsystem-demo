package chatsystem.contacts;

/** Error that is thrown when a contact is added twice to the list. */
public class ContactAlreadyExists extends Exception {

    private final String username;

    public ContactAlreadyExists(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ContactAlreadyExists{" +
                "username='" + username + '\'' +
                '}';
    }
}
