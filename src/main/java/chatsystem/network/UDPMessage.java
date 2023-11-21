package chatsystem.network;

import java.net.InetAddress;
import java.util.Objects;

public record UDPMessage(String content, InetAddress origin) {

}