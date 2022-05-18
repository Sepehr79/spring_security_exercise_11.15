package com.ku.uni.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;

public class CodeAuthentication extends UsernamePasswordAuthenticationToken {

    public CodeAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CodeAuthentication(String token) {
        super("", "", List.of());
        this.setDetails(token);
    }
}
