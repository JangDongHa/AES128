package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;

public class BaseTypeValueCastingWithShort extends BaseTypeValueCasting<Short>{

    @Override
    public String toString(Short data) {
        return data.toString();
    }

    @Override
    public Short toValue(String data) {

        return Short.parseShort(data);
    }
}
