package com.sb.talktalk.talktalkbackend.repository;

import com.sb.talktalk.talktalkbackend.domain.UserState;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStateRepository extends ReactiveMongoRepository<UserState, Integer> {
}
