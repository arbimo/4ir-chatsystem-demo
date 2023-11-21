package chatsystem.network;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UDPTests {

    private static final int TEST_PORT = 1871;

    @Test
    void sendReceiveTest() throws Exception {
        List<String> testMessages = Arrays.asList("alice", "bob", "chloe", "multi\nline string", "éàç");

        List<String> receivedMessages = new ArrayList<>();
        UDPServer server = new UDPServer(TEST_PORT);
        server.addObserver(message -> {
            System.out.println("received: " + message.content());
            receivedMessages.add(message.content());
        });
        server.start();

        for (String msg : testMessages) {
            UDPSender.sendLocalhost(TEST_PORT, msg);
        }

        Thread.sleep(100);
        System.out.println(receivedMessages);
        assertEquals(testMessages.size(), receivedMessages.size());
        assertEquals(testMessages, receivedMessages);
    }
}
