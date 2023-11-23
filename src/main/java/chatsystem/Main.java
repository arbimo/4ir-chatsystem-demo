package chatsystem;

import chatsystem.controller.Controller;
import chatsystem.network.UDPServer;
import chatsystem.ui.View;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import java.net.SocketException;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static final int PORT = 1789;

    public static void main(String[] args) {
        Configurator.setRootLevel(Level.INFO);
        LOGGER.info("Starting ChatSystem application");

        View.initialize();

        try {
            UDPServer server = new UDPServer(PORT);

            server.addObserver(msg -> Controller.handleContactDiscoveryMessage(msg));

            server.start();
        } catch (SocketException e) {
            System.err.println("Could not start UDP server: " + e.getMessage());
            System.exit(1);
        }
    }
}
