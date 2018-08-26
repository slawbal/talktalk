package com.sb.talktalk.talktalkbackend.boundary;

import com.sb.talktalk.talktalkbackend.domain.User;
import com.sb.talktalk.talktalkbackend.domain.UserState;
import com.sb.talktalk.talktalkbackend.repository.UserLastUpdate;
import com.sb.talktalk.talktalkbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestScope
@RequestMapping("/user")
public class UserController {

    private List<User> users = new ArrayList<>();

    @Autowired
    UserRepository repository;

    @Autowired
    UserLastUpdate lastUpdate;

    @GetMapping(value = "/get-user/{id}")
    public Mono<User> getUser(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @GetMapping(value = "/add-dummy")
    public void addDummyUser() {
        repository.save(new User("Johnny", "Cage")).subscribe(u -> lastUpdate.update());
    }

    @GetMapping(value = "/get-all-event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllEvent() {
        return Flux.zip(repository.findAll(), Flux.interval(Duration.ofSeconds(1L))).map(Tuple2::getT1).repeat();
    }

    @GetMapping(value = "/get-all")
    public Flux<User> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/get-last-update", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserState> getLastUpdate() {
        return Flux.zip(lastUpdate.getLastUpdate(), Flux.interval(Duration.ofSeconds(1L))).map(Tuple2::getT1).repeat();
    }

    @PostMapping("/add-user")
    public void addUser(@RequestBody User user) {
        repository.save(user).subscribe(u -> lastUpdate.update());

    }
    @DeleteMapping ("/delete-by-session-id/{sessionID}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String sessionID) {
        Flux<User> users = repository.findBySessionID(Mono.just(sessionID));
        repository.deleteAll(users).doOnSuccess(u -> lastUpdate.update()).subscribe();
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
}
