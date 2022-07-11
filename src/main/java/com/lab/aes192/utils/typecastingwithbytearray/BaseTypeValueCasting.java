package com.lab.aes192.utils.typecastingwithbytearray;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;


public abstract class BaseTypeValueCasting<T> {


    public abstract String toString(T data);

    public abstract T toValue(String data);

    public static String encodeUTF8(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] decodeUTF8(String data){
        return data.getBytes(StandardCharsets.UTF_8);
    }
}
