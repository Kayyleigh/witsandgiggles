package com.application.witsandgiggles.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthManager {

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
