package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;

public class BaseTypeValueCastingWithFloat extends BaseTypeValueCasting<Float>{

    @Override
    public String toString(Float data) {
        return data.toString();
    }

    @Override
    public Float toValue(String data) {

        return Float.parseFloat(data);
    }
}
