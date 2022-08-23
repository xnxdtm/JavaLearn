package com.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wujing
 * @version V1.0
 * @Description test
 */
@SpringBootTest(classes = PublisherApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void test1() {
        amqpTemplate.convertAndSend("myQueue1", "myQueue发送内容");
        System.out.println("myQueue发送内容");
    }

    @Test
    public void test2(){
        amqpTemplate.convertAndSend("amq.fanout","asdfadsf","fanout msg");
        System.out.println("fanout发送成功");
    }

    @Test
    public void test3(){
        amqpTemplate.convertAndSend("amq.topic","com.bjsxt.a.b","topic msg");
        System.out.println("发送成功");
    }
    @Test
    public void test4(){
        amqpTemplate.convertAndSend("amq.topic","com.a.b","topic a msg");
        System.out.println("发送成功");
    }

}
