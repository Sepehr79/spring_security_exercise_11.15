package com.ku.uni.security.auth;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class PasswordAuthentication extends UsernamePasswordAuthenticationToken {

    public PasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PasswordAuthentication(Object principal,
                                  Object code,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(principal, code, authorities);
    }
}
