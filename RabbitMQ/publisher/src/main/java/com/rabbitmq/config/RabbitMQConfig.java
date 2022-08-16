package com.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujing
 * @version V1.0
 * @Description config
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue myQueue1() {
        return new Queue("myQueue1");
    }
}
