package com.penguin.penguinmall.cache.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSentinelServers().setMasterName("mymaster")
                .addSentinelAddress("redis://192.168.100.128:26379")
                .addSentinelAddress("redis://192.168.100.128:26380")
                .addSentinelAddress("redis://192.168.100.128:26381");
        return Redisson.create(config);
    }
}
