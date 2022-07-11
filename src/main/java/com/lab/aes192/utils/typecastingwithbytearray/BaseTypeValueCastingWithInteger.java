package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;

public class BaseTypeValueCastingWithInteger extends BaseTypeValueCasting<Integer>{
    @Override
    public String toString(Integer data) {
        return data.toString();
    }

    @Override
    public Integer toValue(String data) {

        return Integer.parseInt(data);
    }
}
