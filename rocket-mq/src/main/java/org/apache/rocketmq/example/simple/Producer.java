package org.apache.rocketmq.example.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.example.untils.IpAddressConfig;
import org.apache.rocketmq.remoting.common.RemotingHelper;

//简单样例：同步发送消息
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr(IpAddressConfig.getRabbitMqAddress());
        producer.start();
        for (int i = 0; i < 20; i++)
            try {
                {
                    Message msg = new Message("TopicTest", "TagA", "OrderID188",
                            "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                    //同步传递消息，消息会发给集群中的一个Broker节点。
//                    SendResult sendResult = producer.send(msg);
//                    System.out.printf("%s%n", sendResult);
                    producer.sendOneway(msg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        producer.shutdown();
    }
}
