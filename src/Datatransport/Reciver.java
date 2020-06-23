package Datatransport;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Reciver {
    private static String ip = "127.0.0.1";
    private static int recivePort = 15000;

    public static void main(String[] args) throws Exception{
        SocketAddress localAddress = new InetSocketAddress(ip,recivePort);
        DatagramSocket receiveSocket = new DatagramSocket(localAddress);
        while (true){
            byte[] buffer = new byte[128];
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            //阻塞等待数据，收到后存入packet的缓冲区中
            System.out.println("接收方等待数据："+receiveSocket.getLocalSocketAddress());
            receiveSocket.receive(packet);
            byte[] reciveByte = packet.getData();
            System.out.println("接收到的密文为："+reciveByte);
            DesRecive desRecive = new DesRecive(reciveByte,reciveByte.length);
            byte[] byteStr = desRecive.getrecivemsg();
            String finalMsg = new String(byteStr,"UTF-8");
            System.out.println("解密后的明文为："+finalMsg);
        }
    }
}
