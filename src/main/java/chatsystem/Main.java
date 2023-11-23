package chatsystem;

import chatsystem.network.UDPMessage;
import chatsystem.network.UDPSender;
import chatsystem.network.UDPServer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;
import java.net.SocketException;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static final int PORT = 1789;

    static int COUNTER = 0;

    public static void main(String[] args) {
        Configurator.setRootLevel(Level.INFO);
        LOGGER.info("Starting ChatSystem application");
        System.out.println("Welcome to the ChatSystem program");

        try {
            UDPServer server = new UDPServer(PORT);

            server.addObserver(new UDPServer.Observer() {
                @Override
                public void handle(UDPMessage received) {
                    COUNTER += 1;
                    System.out.println("num received: " + COUNTER);
                }
            });


            server.start();
        } catch (SocketException e) {
            System.err.println("Could not start UDP server: " + e.getMessage());
            System.exit(1);
        }

        try {
            UDPSender.sendLocalhost(PORT, "HELLO");
            UDPSender.sendLocalhost(PORT, "HELLO2");
            UDPSender.sendLocalhost(PORT, "HELLO3");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
