package com.geek.spring.security.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationEvents {

    @EventListener
    public void onFailure(AuthorizationDeniedEvent deniedEvent) {
        log.info("Authorization failed for user: {}, due to: {}", deniedEvent.getAuthentication().get().getName(),
                deniedEvent.getAuthorizationResult().toString());
    }
}
