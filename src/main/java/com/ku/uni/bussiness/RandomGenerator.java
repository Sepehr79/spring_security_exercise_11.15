package com.ku.uni.bussiness;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomGenerator {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public String generate4DigitCode(){
        return String.valueOf(SECURE_RANDOM.nextInt(8999) + 1000);
    }

}
