package com.lab.aes192.utils.crypto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Getter
@Setter
@Component
@RequiredArgsConstructor
public class CryptoUtils {
    private static String ALGORITHM = "AES";
    private static String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private static String iv = "283094ades287123";

    private static IvParameterSpec IV;

    private static AES192 aes192 = new AES192();

    public static void encrypt(String key, File inputFile, File outputFile, String transformation)
            throws Exception {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile, transformation);
    }

    public static void decrypt(String key, File inputFile, File outputFile, String transformation) throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile, transformation);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile, String transformation) throws Exception {
        try {
            Key secretKey = aes192.getSecretKey();
            Cipher cipher = Cipher.getInstance(transformation);

            cipher.init(cipherMode, secretKey, aes192.getIV());

            FileInputStream inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            byte[] inputBytes = new byte[(int)inputFile.length()];
            int offset = 0;
            long dataLength = 0;
            while( -1 != (offset = inputStream.read(inputBytes))) {
                cipherOutputStream.write(inputBytes);
                cipherOutputStream.flush();

            }

//                        while(offset < inputFile.length()){
//                int result = inputStream.read(inputBytes);
//                System.out.println(result);
//                if(result == -1){
//                    break;
//                }
//                offset += result;
//                byte[] outputBytes = cipher.doFinal(inputBytes);
//                outputStream.write(outputBytes);
//            }
//            while(offset <inputBytes.length){
//                int result = inputStream.read(inputBytes, offset, inputBytes.length - offset);
//                System.out.println(offset);
//                if(result == -1){
//                    break;
//                }
//                offset += result;
//            }
//            System.out.println(inputFile.length());
//
//            FileOutputStream outputStream = new FileOutputStream(outputFile);
//            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
//            int offset2 = 0;
//            while(offset2 < inputFile.length()) {
//                byte[] outputBytes = cipher.doFinal(inputBytes);
//                outputStream.write();
//
//            }
            inputStream.close();
            outputStream.close();
        } catch (
                NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IOException ex) {
            throw new Exception("Error encrypting/decrypting file", ex);
        }

    }
}
