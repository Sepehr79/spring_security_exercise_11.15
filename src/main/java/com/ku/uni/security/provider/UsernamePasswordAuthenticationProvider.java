package com.ku.uni.security.provider;

import com.ku.uni.bussiness.RandomGenerator;
import com.ku.uni.model.dto.AppUserDetails;
import com.ku.uni.security.auth.PasswordAuthentication;
import com.ku.uni.service.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final AppUserDetailsService appUserDetailsService;

    private final RandomGenerator randomGenerator;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AppUserDetails appUserDetails = (AppUserDetails) appUserDetailsService.loadUserByUsername(authentication.getName());
        String password = (String) authentication.getCredentials();

        if (appUserDetails.getPassword().equals(password)){
            return new UsernamePasswordAuthenticationToken(authentication.getName(), randomGenerator.generate4DigitCode(), authentication.getAuthorities());
        }

        throw new BadCredentialsException(authentication.getName());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PasswordAuthentication.class);
    }
}
