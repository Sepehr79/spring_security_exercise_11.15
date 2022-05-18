package com.ku.uni.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class User {


    private String username;
    private String password;
    private String name;
    private String lastname;
    private Date birthday;
    private List<String> authorities;
    private List<String> roles;

}
