package com.wxss.springbootshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@MapperScan(basePackages = "com.wxss.springbootshiro.mapper")
public class SpringbootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroApplication.class, args);
    }


    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        // 1.创建模板实例
        RedisTemplate redisTemplate = new RedisTemplate();
        // 2.设置序列化方式：还可以设置其他类型的,如:zset,set,list...
        // 2.1设置字符类型的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 2.2设置Hash类型的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }
}
