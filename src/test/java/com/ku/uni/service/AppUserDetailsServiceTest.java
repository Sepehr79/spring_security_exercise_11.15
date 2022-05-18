package com.ku.uni.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
class AppUserDetailsServiceTest {

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Test
    void userDetailsServiceTest(){
        Assertions.assertNotNull(appUserDetailsService.loadUserByUsername("sepehr79"));
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> appUserDetailsService.loadUserByUsername("Wrong_user")
        );
    }

}
