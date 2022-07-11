package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;

public class BaseTypeValueCastingWithLong extends BaseTypeValueCasting<Long>{
    @Override
    public String toString(Long data) {
        return data.toString();
    }

    @Override
    public Long toValue(String data) {

        return Long.parseLong(data);
    }
}
