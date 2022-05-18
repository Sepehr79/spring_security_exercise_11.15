package com.ku.uni.security.jwt;

import com.ku.uni.security.auth.TokenAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class JWTManagerTest {

    private static final JWTManager JWT_MANAGER = new JWTManager();

    @BeforeAll
    static void configJWTManager(){
        ReflectionTestUtils.setField(JWT_MANAGER, "secret", "test");
        JWT_MANAGER.generate();
    }

    @Test
    void testGenerateAndVerifyJWT(){
        String username = "sepehr79";
        List<String> auths = List.of("READ", "WRITE");

        String token = JWT_MANAGER.generateJWT(username, auths);
        log.info("Generated JWT token: {}", token);
        TokenAuthentication tokenAuthentication = JWT_MANAGER.extractAuth(token);
        assertEquals(username, tokenAuthentication.getName());
    }


}
