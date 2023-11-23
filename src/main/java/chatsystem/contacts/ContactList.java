package chatsystem.contacts;

import java.util.ArrayList;
import java.util.List;

/** List of connected users. */
public class ContactList {

    List<Contact> contacts = new ArrayList<>();

    public ContactList() {
    }

    public synchronized void addUser(String username) throws ContactAlreadyExists {
        if (hasUserName(username)) {
            throw new ContactAlreadyExists(username);
        } else {
            Contact contact = new Contact(username);
            contacts.add(contact);
        }
    }

    public synchronized boolean hasUserName(String username) {
        for (Contact contact : contacts) {
            if (contact.username().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public synchronized List<Contact> getAllContacts() {
        // return defensive copy of the contacts to avoid anybody modifying it or doing unsynchronized access
        return new ArrayList<>(this.contacts);
    }
}