package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BaseTypeValueCastingWithLocalDate extends BaseTypeValueCasting<LocalDate> {
    @Override
    public String toString(LocalDate data) {
        return data.toString();
    }

    @Override
    public LocalDate toValue(String data) {

        return LocalDate.parse(data);
    }
}
