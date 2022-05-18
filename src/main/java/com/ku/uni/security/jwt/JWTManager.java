package com.ku.uni.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ku.uni.security.auth.TokenAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class JWTManager {

    @Value("${jwt.secret}")
    private String secret;

    private Algorithm algorithm;

    @PostConstruct
    void generate(){
        algorithm = Algorithm.HMAC256(secret);
    }

    public String generateJWT(String username, List<String> authorities){
        return JWT.create()
                .withClaim("user", username)
                .withClaim("authorities", authorities)
                .sign(algorithm);
    }

    public TokenAuthentication extractAuth(String jwt){
        try {
            DecodedJWT decode = JWT.decode(jwt);
            String username = decode.getClaim("user").asString();
            List<String> authorities = decode.getClaim("authorities").asList(String.class);
            return new TokenAuthentication(username, authorities);
        }catch (JWTDecodeException jwtDecodeException){
            throw new BadCredentialsException("Wrong jwt: " + jwt);
        }
    }


}
