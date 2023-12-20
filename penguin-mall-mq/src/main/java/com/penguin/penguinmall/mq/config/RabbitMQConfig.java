package com.penguin.penguinmall.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    public static final String PENGUINMALL_REGISTRY_QUEUE = "penguinmall.registry.queue";

    /**
     * 用户注册队列
     *
     * @return
     */
    @Bean
    public Queue penguinmallRegistryQueue() {
        return new Queue(PENGUINMALL_REGISTRY_QUEUE, true, false, false,
                new HashMap<String, Object>() {{
                    put("x-queue-type", "classic");
                }});
    }
}

