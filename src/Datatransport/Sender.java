package Datatransport;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

public class Sender {
    private static String ip = "127.0.0.1";
    private static int sendPort = 13000;
    private static int recivePort = 15000;

    public static void main(String[] args) throws Exception{
        SocketAddress localAddress = new InetSocketAddress(ip,sendPort);
        DatagramSocket senderSocket = new DatagramSocket(localAddress);
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
                    count++;
                    String message = scanner.nextLine();
                    DesSend desSend = new DesSend(message);
                    byte buffer[] = desSend.getsendmsg();
                    SocketAddress destAddress = new InetSocketAddress(ip, recivePort);
                    DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, destAddress);
                    senderSocket.send(datagramPacket);
                    System.out.println("数据已经发送：" + count);
                    Thread.sleep(1000);
                }
    }
}