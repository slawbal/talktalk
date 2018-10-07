package com.sb.talktalk.frontend.boundary;

import com.sb.talktalk.frontend.domain.User;
import com.sb.talktalk.frontend.domain.UserState;
import com.sb.talktalk.frontend.rest.ReactRestClient;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringComponent
@UIScope
public class UserService {

    @Autowired
    ReactRestClient restClient;

    public Mono<User> getUser() {
        return restClient.getForSingleObject("user/", User.class);
    }

    public Flux<User> getAllUsers() {
        return restClient.getForObject("user/", User.class);
    }

    public Flux<User> getAllUsersAsEvent() {
        return restClient.getForObject("user/event", User.class);
    }

    public Mono<User> addUser(final User user) {
        return restClient.postObject("user/", Mono.just(user), User.class);
    }

    public Mono<Boolean> deleteUserBySessionID(final String sessionID) {
        return restClient.deleteObject("user/by-session-id/{sessionID}", sessionID);
    }

    public Flux<UserState> getUserLastUpdate() {
        return restClient.getForObject("user/get-last-update", UserState.class);
    }
}
