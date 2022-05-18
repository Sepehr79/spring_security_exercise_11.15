package com.ku.uni.security.provider;

import com.ku.uni.model.entity.TemporaryUser;
import com.ku.uni.model.repo.TemporaryUserRepo;
import com.ku.uni.security.auth.CodeAuthentication;
import com.ku.uni.security.jwt.JWTManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CodeAuthenticationProvider implements AuthenticationProvider {

    private final TemporaryUserRepo temporaryUserRepo;

    private final JWTManager jwtManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String code = (String) authentication.getCredentials();

        Optional<TemporaryUser> temporaryUserOptional = temporaryUserRepo.findById(username);
        if (temporaryUserOptional.isPresent() && temporaryUserOptional.get().getCode().equals(code)){
            TemporaryUser temporaryUser = temporaryUserOptional.get();
            String token = jwtManager.generateJWT(username, temporaryUser.getAuthorities());
            return new CodeAuthentication(token);
        }


        throw new BadCredentialsException(username);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(CodeAuthentication.class);
    }
}
