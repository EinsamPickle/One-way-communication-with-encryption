package Datatransport;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class ReceiverDes {
    private static int port = 15005;

    public static void main(String args[]) throws Exception {

        // 1.创建要用来接收的本地地址对象
        SocketAddress localAddr = new InetSocketAddress("127.0.0.1", port);
        // 2.接收的服务器UDP端口
        DatagramSocket recvSocket = new DatagramSocket(localAddr);
        while (true) {
            // 3.指定接收缓冲区大小
            byte[] buffer = new byte[128];
            // 4.创建接收数据包对象,指定接收大小
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            recvSocket.receive(packet);

            // 转换接收到的数据为字符串

            byte[] recivebyte = packet.getData();

            ReciveSecret rs = new ReciveSecret(recivebyte, recivebyte.length);
            byte[] bytestr = rs.getrecivemsg();

            String finalmsg = new String(bytestr, "utf-8");
            FileOutputStream fileOutputStreamDes = new FileOutputStream("R_despassword.key");
            ObjectOutputStream objectOutputStreamDes = new ObjectOutputStream(fileOutputStreamDes);
            objectOutputStreamDes.writeObject(finalmsg);
            objectOutputStreamDes.flush();
            objectOutputStreamDes.close();
            System.out.println("DES密钥接收方写入");

        }
    }
}
