package com.sb.talktalk.frontend.rest;

import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringComponent
public class ReactRestClient {

    private static final String DEFAULT_SERVER_URL = "localhost:8090";
    private static final String SERVER_ADDR_PATTERN = "http://%s/";
    private static final String SERVER_ADDR = "VR_SERVER_ADDR";
    private static final String SERVER_URL = getBaseUri();

    private WebClient client = WebClient.create(SERVER_URL);

    private static String getBaseUri() {
        String addr = SystemEnv.getServerAddress(SERVER_ADDR, DEFAULT_SERVER_URL);
        return String.format(SERVER_ADDR_PATTERN, addr);
    }

    public <T> Flux<T> getForObject(String path, Class<T> objectType) {
        return client
                .get()
                .uri(createURL(path))
                .exchange()
                .flatMapMany(res -> res.bodyToFlux(objectType))
                .log();
    }

    public <T> Mono<T> getForSingleObject(String path, Class<T> objectType) {
        return client
                .get()
                .uri(createURL(path))
                .exchange()
                .flatMap(res -> res.bodyToMono(objectType))
                .log();
    }

    public <T> Mono<T> postObject(String path, Mono<T> object, Class<T> objectType) {
        return client
                .post()
                .uri(createURL(path))
                .accept(MediaType.APPLICATION_JSON)
                .body(object, objectType)
                .retrieve()
                .bodyToMono(objectType).log();
    }

    public Mono<Boolean> deleteObject(String path, String sessionID) {
        return client
                .delete()
                .uri(createURL(path), sessionID)
                .exchange().map(ClientResponse::statusCode).map(HttpStatus::isError);
    }

    private static String createURL(String path) {
        return SERVER_URL + path;
    }
}
