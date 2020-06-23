package Datatransport;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;

public class DesRecive {
    private byte[] recivebyte=null;

    public DesRecive(byte[] recivebyte,int len) {
        this.recivebyte=new byte[len];
        for(int i=0;i<recivebyte.length;i++){
            this.recivebyte[i] = recivebyte[i];
        }
    }

    public byte[] getrecivemsg() throws Exception {
        FileInputStream fins = new FileInputStream("R_despassword.key");
        String password = fins.toString();
        SecureRandom random = new SecureRandom();
        DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        fins.close();

        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding ");

        cipher.init(Cipher.DECRYPT_MODE, secretKey,random);

        // 返回解密后的内容

        return cipher.doFinal(recivebyte);

    }
}
