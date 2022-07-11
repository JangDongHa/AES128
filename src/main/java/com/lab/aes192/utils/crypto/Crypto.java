package com.lab.aes192.utils.crypto;

public interface Crypto {
    public String aesEncode(String data, String mode);
    public byte[] aesDecode(String cipherText, String mode);

    private void setSecretKey() {

    }

    private void setIV() {

    }

    public static String CBCMode() {
        return null;
    }

    public static String CBCNoPaddingMode() {
        return null;
    }

    public static String CTRMode() {
        return null;
    }
}
