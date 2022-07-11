package com.lab.aes192.utils.typecastingwithbytearray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;

public class BaseTypeValueCastingWithMap extends BaseTypeValueCasting<Map<String, Object>>{
    @Override
    public String toString(Map<String, Object> data) {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            try {
                jsonObject.put(key, value);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonObject.toString();

    }

    @Override
    public Map<String, Object> toValue(String data) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(data, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
