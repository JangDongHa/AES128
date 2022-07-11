package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public class BaseTypeValueCastingWithByteArray extends BaseTypeValueCasting<byte[]>{

    @Override
    public String toString(byte[] data) {
        return new String(data);
    }

    @Override
    public byte[] toValue(String data) {

        try {
            return data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
