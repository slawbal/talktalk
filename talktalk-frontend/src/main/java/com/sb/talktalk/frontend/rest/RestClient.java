package com.sb.talktalk.frontend.rest;

import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringComponent
public class RestClient<T> {

    private static final String DEFAULT_SERVER_URL = "localhost:8090";
    private static final String SERVER_ADDR_PATTERN = "http://%s/";
    private static final String SERVER_ADDR = "VR_SERVER_ADDR";
    private static final String SERVER_URL = getBaseUri();

    private final RestTemplate restTemplate = new RestTemplate();

    public static String getBaseUri() {
        String addr = SystemEnv.getServerAddress(SERVER_ADDR, DEFAULT_SERVER_URL);
        return String.format(SERVER_ADDR_PATTERN, addr);
    }

    private static String createURL(String path) {
        return SERVER_URL + path;
    }

    public T getForObject(String path, Class<T> objectType) {
        return restTemplate.getForObject(createURL(path), objectType);
    }

    public List<T> getForCollection(String path, Class<T[]> objectType) {
        ResponseEntity<T[]> entity = restTemplate.getForEntity(createURL(path), objectType);
        return Arrays.asList(entity.getBody());
    }

    public void postObject(String path, Object object, Class<T> objectType) {
        restTemplate.postForEntity(createURL(path), object, objectType);
    }
}
