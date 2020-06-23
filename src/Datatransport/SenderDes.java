package Datatransport;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Random;

public class SenderDes {
    private static int ports = 13000;
    private static int portr = 15005;

    public static void main(String args[]) throws Exception {
        Random random = new Random();
        int[] p =new int[8];
        StringBuilder password = new StringBuilder("");
        for (int i=0;i<8;i++) {
            p[i]=random.nextInt(10);
            System.out.println(p[i]);
            password.append(p[i]);
        }
        String pp = password.toString();
        FileOutputStream fileOutputStreamDes = new FileOutputStream("despassword.key");
        ObjectOutputStream objectOutputStreamDes = new ObjectOutputStream(fileOutputStreamDes);
        objectOutputStreamDes.writeObject(pp);
        objectOutputStreamDes.flush();
        objectOutputStreamDes.close();
        System.out.println("DES密钥本地写入");
        // 1.创建要用来发送的本地地址对象
        SocketAddress localAddr = new InetSocketAddress("127.0.0.1", ports);
        // 2.创建发送的Socket对象
        DatagramSocket dSender = new DatagramSocket(localAddr);
            SendSecret ss = new SendSecret(password.toString());
            byte[] bytestr = ss.getsendmsg();
            // 4.发送数据的目标地址和端口
            SocketAddress destAdd = new InetSocketAddress("127.0.0.1", portr);
            // 5.创建要发送的数据包,指定内容,指定目标地址
            DatagramPacket dp = new DatagramPacket(bytestr, bytestr.length, destAdd);
            dSender.send(dp);// 6.发送
            System.out.println("数据已发送");
            Thread.sleep(1000);
        }
    }
