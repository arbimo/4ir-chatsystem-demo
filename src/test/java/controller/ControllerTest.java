package controller;

import chatsystem.contacts.ContactList;
import chatsystem.controller.Controller;
import chatsystem.network.UDPMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ControllerTest {

    @BeforeEach
    void clearContactList() {
        ContactList.getInstance().clear();
    }

    @Test
    void messageHandlingTest() throws UnknownHostException {
        ContactList contacts = ContactList.getInstance();
        UDPMessage msg1 = new UDPMessage("alice", InetAddress.getByName("10.5.5.1"));
        UDPMessage msg2 = new UDPMessage("bob", InetAddress.getByName("10.5.5.2"));

        assert !contacts.hasUserName("alice");
        Controller.handleContactDiscoveryMessage(msg1);
        assert contacts.hasUserName("alice");

        assert !contacts.hasUserName("bob");
        Controller.handleContactDiscoveryMessage(msg2);
        assert contacts.hasUserName("bob");

        Controller.handleContactDiscoveryMessage(msg2);
    }
}
