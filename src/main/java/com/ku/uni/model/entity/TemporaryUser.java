package com.ku.uni.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(timeToLive = 60)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TemporaryUser {

    @Id
    private String username;
    private String code;
    private List<String> authorities;

}
