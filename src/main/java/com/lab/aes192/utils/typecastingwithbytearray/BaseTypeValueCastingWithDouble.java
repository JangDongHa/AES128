package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;

public class BaseTypeValueCastingWithDouble extends BaseTypeValueCasting<Double> {


    @Override
    public String toString(Double data) {
        return data.toString();
    }

    @Override
    public Double toValue(String data) {
        return Double.parseDouble(data);
    }
}
