package com.lab.aes192.utils.random;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class Random {

    private java.util.Random random = new java.util.Random();

    //원하는 자릿수 입력
    public String getRandomHexString(int size){
        StringBuffer sb = new StringBuffer();
        while(sb.length() < size){
            sb.append(Integer.toHexString(random.nextInt()));
        }

        return sb.toString().substring(0, size);
    }

    public String getRandomString(int size){
        byte[] array = new byte[size]; // length is bounded by 7
        random.nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

    public java.util.Random getRandom() {
        return random;
    }

    public int getYear(){
        return random.nextInt(2038-1970 )+1970;
    }

    public int getMonth(){
        return random.nextInt(11)+1;
    }

    public int getDay(int month){
        switch (month){
            case 1, 3, 5, 7, 8, 10, 12:
                return random.nextInt(30) + 1;
            case 2:
                return random.nextInt(28) + 1;
            case 4, 6, 9, 11:
                return random.nextInt(29) + 1;
        }
        return 0;
    }

    public int getHour(){
        return random.nextInt(23);
    }
    public int getMinute(){
        return random.nextInt(59);
    }
    public int getSecond(){
        return random.nextInt(60);
    }
    public int getNanoSecond(){
        return random.nextInt(9999999);
    }

    public LocalDate getLocalDate(){
        int randYear = getYear();
        int randMonth = getMonth();
        int randDay = getDay(randMonth);
        LocalDate dummyDate = LocalDate.of(randYear, randMonth, randDay);
        return dummyDate;
    }

    public LocalTime getLocalTime(){

        LocalTime localTime = LocalTime.of(getHour(), getMinute(), getSecond(), getNanoSecond());
        return localTime;
    }

    public BigDecimal getBigDecimal(int intBoundary, int floatBoundary){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < intBoundary-floatBoundary ; i++){
            sb.append(random.nextInt(9));
        }
        sb.append(".");
        for(int i = 0; i < floatBoundary; i++){
            sb.append(random.nextInt(9));
        }
        BigDecimal bigDecimal = new BigDecimal(sb.toString());
        return bigDecimal;
    }

    public Short getShort(){
        return (short) (random.nextInt(Short.MAX_VALUE -Short.MIN_VALUE) + Short.MAX_VALUE);
    }

    public Long getMediumInt(){
        return (long) random.nextInt(8388607 + 8388608)-8388608;
    }


}
