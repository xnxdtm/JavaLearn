package com.rabbitmq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wujing
 * @version V1.0
 * @Description
 */
@Component
public class ReceiveDemo {
    @RabbitListener(queues = "myQueue1")
    public void demo(String msg) {
        System.out.println("获取到的消息1111： " + msg);
    }
}
