package com.sb.talktalk.talktalkbackend.boundary;

import com.sb.talktalk.talktalkbackend.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private WebTestClient webTestClient;

    @Before
    public void init() {
        this.webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8090").build();
    }

    @Test
    public void getUser_shouldReturn404NotFound_whenUserIsNotPresent() {
        this.webTestClient.get()
                .uri("/user/get-user/Cage")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void getUser_shouldReturnOk_whenUserIsPresent() {
        this.webTestClient.get()
                .uri("/user/add-dummy")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();

        this.webTestClient.get()
                .uri("/user/get-user/Cage")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectBody(User.class).isEqualTo(new User("Johnny", "Cage"));
    }
}