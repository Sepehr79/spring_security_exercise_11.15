package com.ku.uni.security;

import com.ku.uni.security.jwt.JWTManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomAuthManager customAuthManager;

    private final JWTManager jwtManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterAt(new CustomAuthFilter(customAuthManager, jwtManager), BasicAuthenticationFilter.class);
    }

}
