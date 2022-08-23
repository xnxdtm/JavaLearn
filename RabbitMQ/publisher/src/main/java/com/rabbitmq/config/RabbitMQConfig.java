package com.rabbitmq.config;

import org.springframework.amqp.core.*;
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

    @Bean
    protected FanoutExchange fanoutExchange(){
        return new FanoutExchange("amq.fanout");
    }

    @Bean
    protected Queue fanoutQuque1(){
        return new Queue("fanout1");
    }

    @Bean
    protected Queue fanoutQuque2(){
        return new Queue("fanout2");
    }

    @Bean
    protected Binding fanoutBinding(Queue fanoutQuque1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQuque1).to(fanoutExchange);
    }

    @Bean
    protected Binding fanoutBinding2(Queue fanoutQuque2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQuque2).to(fanoutExchange);
    }

    @Bean
    protected TopicExchange topicExchange() {
        return new TopicExchange("amq.topic");
    }

    @Bean
    protected Queue topicQueue() {
        return new Queue("topic1");
    }

    @Bean
    protected Queue topicQueue2() {
        return new Queue("topic2");
    }

    @Bean
    protected Binding topicBinding(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("com.a");
    }

    @Bean
    protected Binding topicBinding2(Queue topicQueue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("com.#");
    }

}
