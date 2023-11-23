package chatsystem.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/** UDP server that (once started) listens indefinitely on a given port. */
public class UDPServer extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(UDPServer.class);

    /** Interface that observers of the UDP server must implement. */
    public interface Observer {
        /** Method that is called each time a message is received. */
        void handle(UDPMessage received);
    }


    private final DatagramSocket socket;
    private final List<Observer> observers = new ArrayList<>();

    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    /** Adds a new observer to the class, for which the handle method will be called for each incoming message. */
    public void addObserver(Observer obs) {
        synchronized (this.observers) {
            this.observers.add(obs);
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                byte[] buff = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buff, buff.length);

                // wait for the next message
                socket.receive(packet);

                // extract and print message
                String received = new String(packet.getData(), 0, packet.getLength());
                UDPMessage message = new UDPMessage(received, packet.getAddress());

                LOGGER.trace("Received on port " + socket.getLocalPort() + ": " + message.content() + " from " + message.origin());

                synchronized (this.observers) {
                    for (Observer obs : this.observers) {
                        obs.handle(message);
                    }
                }
            } catch (IOException e) {
                System.err.println("Receive error: " + e.getMessage());
            }
        }
    }
}
