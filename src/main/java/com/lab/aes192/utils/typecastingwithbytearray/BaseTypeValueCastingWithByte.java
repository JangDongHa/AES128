package com.lab.aes192.utils.typecastingwithbytearray;

import java.math.BigDecimal;

public class BaseTypeValueCastingWithByte extends BaseTypeValueCasting<Byte>{

    @Override
    public String toString(Byte data) {
        return data.toString();
    }

    @Override
    public Byte toValue(String data) {
        Byte value = Byte.parseByte(data);
        return value;
    }
}
