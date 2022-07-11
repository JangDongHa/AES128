package com.lab.aes192.utils.dummyjsonrequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
public class DummyJsonRequest {

    private static final String URI = "https://jsonplaceholder.typicode.com";
    private static final String POST_ROUTER_PATTERN = "/posts/%d";
    private static final String COMMENT_ROUTER_PATTERN = "/posts/%d/comments";
    private static final String USER_ROUTER_PATTERN = "/users";

    private static final String PHOTOS_ROUTER_PATTERN = "/photos";

    private static final int TIMEOUT = 5000;

    private final ObjectMapper objectMapper = new ObjectMapper();


    private RestTemplate restTemplate;

    public DummyJsonRequest(){
        restTemplate = new RestTemplate(setState());
    }

    public Map<String, Object> getPostWithId(int postId){
        Map<String, Object> result = null;
        String postUrl = getPostUrl(postId);
        System.out.println(postUrl);
        HttpEntity<?> entity = createHttpEntity();

        ResponseEntity<?> resultMap = getResultMap(postUrl, HttpMethod.GET, entity, String.class);

        try {
            JSONObject jsonObject = new JSONObject(resultMap.getBody().toString());
            result = objectMapper.readValue(jsonObject.toString(), Map.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public JSONArray getCommentWithPostId(int postId){
        JSONArray result = null;
        String commentUrl = getCommentUrlWithPostId(postId);
        System.out.println(commentUrl);
        return getJsonArray(commentUrl);
    }

    public JSONArray getUsers(){
        JSONArray result = null;
        String userUrl = getUserUrl();
        return getJsonArray(userUrl);
    }

    public JSONArray getPhotos(){
        JSONArray result = null;
        String photosUrl = getPhotosUrl();
        return getJsonArray(photosUrl);
    }

    private JSONArray getJsonArray(String url) {
        JSONArray result;
        HttpEntity<?> entity = createHttpEntity();

        ResponseEntity<?> resultMap = getResultMap(url, HttpMethod.GET, entity , String.class);

        try {
            String jsonData = resultMap.getBody().toString();
            result = new JSONArray(jsonData);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    private ResponseEntity<?> getResultMap(String url, HttpMethod httpMethod, HttpEntity entity, Class classOrient){
        return restTemplate.exchange(url, httpMethod, entity, classOrient);
    }

    private HttpComponentsClientHttpRequestFactory setState(){
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(TIMEOUT); //타임아웃 설정 5초
        factory.setReadTimeout(TIMEOUT);//타임아웃 설정 5초
        return factory;
    }

    private HttpEntity<?> createHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<>(headers);
    }


    private String getUserUrl(){
        return URI + USER_ROUTER_PATTERN;
    }

    private String getCommentUrlWithPostId(int postId){
        String url = URI + COMMENT_ROUTER_PATTERN;
        return String.format(url, postId);
    }

    private String getPostUrl(int postId){
        String url = URI + POST_ROUTER_PATTERN;
        return String.format(url,postId);
    }

    private String getPhotosUrl(){
        return URI + PHOTOS_ROUTER_PATTERN;
    }

}
