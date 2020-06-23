package Datatransport;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;

public class DesSend {
    String str;

    public DesSend(String str) {
        this.str = str;
    }

    public byte[] getsendmsg() throws Exception {
        FileInputStream fins = new FileInputStream("despassword.key");
        String password = fins.toString();
        SecureRandom secureRandom = new SecureRandom();
        DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

        fins.close();

        byte[] srcData = str.getBytes("utf-8");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding ");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey,secureRandom);
        // 返回加密后的内容

        return cipher.doFinal(srcData);
    }
}

