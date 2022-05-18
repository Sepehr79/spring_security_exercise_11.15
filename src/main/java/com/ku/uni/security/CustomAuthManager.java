package com.ku.uni.security;

import com.ku.uni.model.entity.TemporaryUser;
import com.ku.uni.model.repo.TemporaryUserRepo;
import com.ku.uni.security.provider.CodeAuthenticationProvider;
import com.ku.uni.security.provider.UsernamePasswordAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomAuthManager implements AuthenticationManager {

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    private final CodeAuthenticationProvider codeAuthenticationProvider;

    private final TemporaryUserRepo temporaryUserRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (usernamePasswordAuthenticationProvider.supports(authentication.getClass())){
            Authentication authenticate = usernamePasswordAuthenticationProvider.authenticate(authentication);
            TemporaryUser temporaryUser = TemporaryUser.builder()
                    .username(authenticate.getName())
                    .authorities(
                            authenticate.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList())
                    )
                    .code((String) authenticate.getCredentials())
                    .build();
            temporaryUserRepo.save(temporaryUser);
            return authenticate;
        }
        else if (codeAuthenticationProvider.supports(authentication.getClass())){
            return codeAuthenticationProvider.authenticate(authentication);
        }
        return null;
    }
}
