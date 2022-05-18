package com.ku.uni.configuration;

import com.ku.uni.model.entity.TemporaryUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {


    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new JedisConnectionFactory(new RedisStandaloneConfiguration());
    }

    @Bean
    public RedisTemplate<String, TemporaryUser> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, TemporaryUser> stringTemporaryUserRedisTemplate = new RedisTemplate<>();
        stringTemporaryUserRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringTemporaryUserRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringTemporaryUserRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(TemporaryUser.class));
        return stringTemporaryUserRedisTemplate;
    }

}
