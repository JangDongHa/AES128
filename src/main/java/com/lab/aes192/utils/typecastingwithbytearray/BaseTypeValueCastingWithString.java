package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;

public class BaseTypeValueCastingWithString extends BaseTypeValueCasting<String> {

    @Override
    public String toString(String data) {
        return data;
    }

    @Override
    public String toValue(String data) {

        return data;
    }
}
