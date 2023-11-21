package chatsystem.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/** Create a UDP server that (once started) listens indefinitely on a given port. */
public class UDPServer extends Thread {

    private final DatagramSocket socket;

    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        while(true) {
            try {
                byte[] buff = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buff, buff.length);

                // wait for the next message
                socket.receive(packet);

                // extarct and print message
                String received = new String(packet.getData(), 0, packet.getLength());
                UDPMessage message = new UDPMessage(received, packet.getAddress());
                System.out.println("Received: " + message);
            } catch (IOException e) {
                System.err.println("Receive error: " + e.getMessage());
            }
        }
    }
}
