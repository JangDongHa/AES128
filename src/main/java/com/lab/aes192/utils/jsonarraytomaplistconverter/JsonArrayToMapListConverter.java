package com.lab.aes192.utils.jsonarraytomaplistconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class JsonArrayToMapListConverter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, Object>> jsonArrayToMapListConverter(JSONArray array){

        List<Map<String, Object>> result = new LinkedList<>();

        for(int i = 0 ; i < array.length(); i++){
            try {
                Map<String, Object> data = objectMapper.readValue(array.get(i).toString(), Map.class);
                result.add(data);
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public JSONArray MapListToJsonArrayConverter(List<Map<String, Object>> arr){

        JSONArray jsonArray = new JSONArray();

        for(int i = 0 ; i < arr.size(); i++){
            JSONObject data = new JSONObject(arr.get(i));
            jsonArray.put(data);
        }
        return jsonArray;
    }
}
