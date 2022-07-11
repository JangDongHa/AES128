package com.lab.aes192.utils.crypto;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@Getter
public class AES128 {

    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;

    private static final String CBC_MODE = "/CBC";
    private static final String CTR_MODE = "/CTR";
    private static final String PKCS5PADDING = "/PKCS5Padding";

    private static final String NOPADDING = "/NoPadding";
    private String key = "password12345678"; //16 byte

    private static String iv = "7238497249837243";

    private SecretKeySpec secretKey;
    private IvParameterSpec IV;


    public AES128() {
        validation(key);
        setSecretKey();
        setIV();
    }

    public String aesEncode(String str, String mode) throws Exception {
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);
        byte[] encrypted = cipher.doFinal(str.getBytes(ENCODING_TYPE));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public byte[] aesDecode(String str, String mode) throws Exception {
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IV);
        return cipher.doFinal(Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE)));
    }

    private void validation(final String key) {
        Optional.ofNullable(key)
                .filter(Predicate.not(String::isBlank))
                .filter(Predicate.not(s -> s.length() != 16))
                .orElseThrow(IllegalArgumentException::new);
    }

    private void setSecretKey(){
        secretKey = new SecretKeySpec(key.getBytes(ENCODING_TYPE), "AES");
    }

    private void setIV(){
        IV = new IvParameterSpec(iv.getBytes(ENCODING_TYPE));
    }

    public static String CBCMode(){
        return "AES"+CBC_MODE+PKCS5PADDING;
    }

    public static String CBCNoPaddingMode() { return "AES"+CBC_MODE+NOPADDING;}

    public static String CTRMode(){
        return "AES"+CTR_MODE+"/NoPadding";
    }
}