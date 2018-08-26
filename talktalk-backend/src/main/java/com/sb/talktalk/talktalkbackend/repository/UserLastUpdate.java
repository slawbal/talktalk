package com.sb.talktalk.talktalkbackend.repository;

import com.sb.talktalk.talktalkbackend.domain.UserState;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserLastUpdate {

    @Autowired
    UserStateRepository stateRepository;

    public void update() {
        stateRepository.save(new UserState(1, DateTime.now().getMillis())).subscribe();

    }

    public Mono<UserState> getLastUpdate() {
        return stateRepository.findById(1);
    }
}
