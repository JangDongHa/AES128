package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalTime;

public class BaseTypeValueCastingWithLocalTime extends BaseTypeValueCasting<LocalTime>{

    @Override
    public String toString(LocalTime data) {
        return data.toString();
    }

    @Override
    public LocalTime toValue(String data) {

        return LocalTime.parse(data);
    }
}
