package chatsystem.controller;

import chatsystem.contacts.ContactAlreadyExists;
import chatsystem.contacts.ContactList;
import chatsystem.network.UDPMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public static void handleContactDiscoveryMessage(UDPMessage message) {
        String username = message.content();
        try {
            ContactList.getInstance().addUser(username);
            LOGGER.info("New Contact added to the list: " + username);
        } catch (ContactAlreadyExists e) {
            LOGGER.error("Received duplicated contact: " + username);
        }
    }
}
