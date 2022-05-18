package com.ku.uni.model.repo;

import com.ku.uni.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final List<User> USERS = List.of(
            buildUser("user", "1234", "person", "person"),
            buildUser("sepehr79", "1234", "sepehr", "mollaei"),
            buildUser("ali79", "1234", "ali", "a"),
            buildUser("sam79", "1234", "sam", "b"),
            buildUser("ahmad79", "1234", "ahmad", "c")
    );

    public Optional<User> findByUsername(String username){
        return USERS.stream().filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    private static User buildUser(String username, String password, String name, String lastname){
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .lastname(lastname)
                .build();
    }

}
