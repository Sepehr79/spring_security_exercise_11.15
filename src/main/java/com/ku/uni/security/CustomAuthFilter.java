package com.ku.uni.security;


import com.ku.uni.security.auth.CodeAuthentication;
import com.ku.uni.security.jwt.JWTManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    private final JWTManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("user");
        String code     = request.getHeader("code");
        String password = request.getHeader("pass");
        String token    = request.getHeader("token");

        if (token != null){
            var tokenAuthentication = jwtManager.extractAuth(token);
            var context = SecurityContextHolder.getContext();
            context.setAuthentication(tokenAuthentication);
            filterChain.doFilter(request, response);
        }else if (code != null){
            var codeAuth = new CodeAuthentication(username, code);
            Authentication authenticate = authenticationManager.authenticate(codeAuth);
            response.setHeader("Token", (String) authenticate.getDetails());
            response.setStatus(HttpStatus.ACCEPTED.value());
        }else if (password != null){
            var passwordAuth = new UsernamePasswordAuthenticationToken(username, password);
            String credentials = (String) authenticationManager.authenticate(passwordAuth).getCredentials();
            response.setHeader("code", credentials);
            response.setStatus(HttpStatus.ACCEPTED.value());
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

    }
}
