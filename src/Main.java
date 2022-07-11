import javax.crypto.Cipher;

public class Main {
    public static void main(String[] args) throws Exception {
        AES128 aes128 = new AES128();

        String plain = "wkdehdgk";
        String mode = AES128.CBCNoPaddingMode(); // get in mode here

        String encPlain = aes128.aesEncode(plain, AES128.CBCMode());
        byte[] decBytes = aes128.aesDecode(encPlain, AES128.CBCMode());

        System.out.println(encPlain);
        System.out.println(new String(decBytes));

    }
}
