package com.sb.talktalk.talktalkbackend.repository;

import com.sb.talktalk.talktalkbackend.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findBySessionID(Mono<String> sessionID);
}
