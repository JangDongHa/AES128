package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;

public class BaseTypeValueCastingWithBigDecimal extends BaseTypeValueCasting<BigDecimal>{

    @Override
    public String toString(BigDecimal data) {
        return data.toString();
    }

    @Override
    public BigDecimal toValue(String data) {
        BigDecimal value = new BigDecimal(data);
        return value;
    }
}
