package com.lab.aes192.utils.crypto;

import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCasting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Component
@Getter
public class AES192 {

    private String key = "123456789012345678901234"; //24byte
    private SecretKeySpec secretKey;

    private static String iv = "7238497249837243";

    private IvParameterSpec IV;
    private static final String CBC_MODE = "/CBC";
    private static final String CTR_MODE = "/CTR";
    private static final String PKCS5PADDING = "/PKCS5Padding";

    private static final String NOPADDING = "/NoPadding";

    public AES192(){
        setSecretKey();
        setIV();
    }

    public String aesEncode(String data, String mode){
        byte[] encryptionByte = null;
        try {
            Cipher cipher = Cipher.getInstance(mode);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);

            encryptionByte = cipher.doFinal(data.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(encryptionByte);
    }

    public byte[] aesDecode(String cipherText, String mode) {
        Cipher cipher = null;
        byte[] decryptionByte = null;
        try {
            cipher = Cipher.getInstance(mode);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, IV);

            decryptionByte = cipher.doFinal(Base64.getDecoder().decode(cipherText));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
        return decryptionByte;

    }

    private void setSecretKey(){
        secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
    }

    private void setIV(){
        try {
            IV = new IvParameterSpec(iv.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }



    public static String CBCMode(){
            return "AES"+CBC_MODE+PKCS5PADDING;
    }

    public static String CBCNoPaddingMode() { return "AES"+CBC_MODE+NOPADDING;}

    public static String CTRMode(){
        return "AES"+CTR_MODE+"/NoPadding";
    }

}
