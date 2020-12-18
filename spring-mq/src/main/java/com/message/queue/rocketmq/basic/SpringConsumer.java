package com.message.queue.rocketmq.basic;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * ConsumeMode 指定消费模式
 */
@Component
@RocketMQMessageListener(consumerGroup = "MyConsumerGroup", topic = "TestTopic", consumeMode = ConsumeMode.CONCURRENTLY)
public class SpringConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("Received message : " + message);
    }

}
