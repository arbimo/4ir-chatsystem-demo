package chatsystem;


import chatsystem.network.UDPSender;
import chatsystem.network.UDPServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

    public static final int PORT = 1789;

    public static void main(String[] args) {
        System.out.println("Welcome to the ChatSystem program");

        try {
            UDPServer server = new UDPServer(PORT);
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
