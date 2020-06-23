package Datatransport;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;

public class ReciveSecret {
    private byte[] recivebyte=null;

    public ReciveSecret(byte[] recivebyte,int len) {
        this.recivebyte=new byte[len];
        for(int i=0;i<recivebyte.length;i++){
            this.recivebyte[i] = recivebyte[i];
        }
    }

    public byte[] getrecivemsg() throws Exception {
        FileInputStream fins = new FileInputStream("private.key");
        ObjectInputStream ois = new ObjectInputStream(fins);
        RSAPrivateKey privateKey = (RSAPrivateKey) ois.readObject();
        fins.close();

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // 返回解密后的内容

        return cipher.doFinal(recivebyte);

    }

}