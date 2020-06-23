package Datatransport;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

public class SendSecret {
    String str;

    public SendSecret(String str) {
        this.str = str;
    }

    public byte[] getsendmsg() throws Exception {
        FileInputStream fins = new FileInputStream("public.key");
        ObjectInputStream ois = new ObjectInputStream(fins);
        RSAPublicKey publicKey = (RSAPublicKey) ois.readObject();
        fins.close();

        byte[] srcData = str.getBytes("UTF-8");

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // 返回加密后的内容

        return cipher.doFinal(srcData);
    }
}