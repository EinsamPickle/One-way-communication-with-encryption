package Datatransport;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSA {
    public static void main(String[] args) throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //取得RSA算法生成器对象
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //生成密钥对
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        FileOutputStream fileOutputStreamPublic = new FileOutputStream("public.key");//写入公钥
        ObjectOutputStream objectOutputStreamPublic = new ObjectOutputStream(fileOutputStreamPublic);
        objectOutputStreamPublic.writeObject(publicKey);
        objectOutputStreamPublic.flush();
        objectOutputStreamPublic.close();
        System.out.println("公钥写入");

        FileOutputStream fileOutputStreamPrivate = new FileOutputStream("private.key");
        ObjectOutputStream objectOutputStreamPrivate = new ObjectOutputStream(fileOutputStreamPrivate);
        objectOutputStreamPrivate.writeObject(privateKey);
        objectOutputStreamPrivate.flush();
        objectOutputStreamPrivate.close();
        System.out.println("私钥写入");
    }
}
