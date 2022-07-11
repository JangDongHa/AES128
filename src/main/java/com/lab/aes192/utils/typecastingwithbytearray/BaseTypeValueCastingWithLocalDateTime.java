package com.lab.aes192.utils.typecastingwithbytearray;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BaseTypeValueCastingWithLocalDateTime extends BaseTypeValueCasting<LocalDateTime> {
    @Override
    public String toString(LocalDateTime data) {
        return data.toString();
    }

    @Override
    public LocalDateTime toValue(String data) {

        return LocalDateTime.parse(data);
    }
}
