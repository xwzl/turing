package com.turing.java.jvm.concurrent.pool.schedule;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class EurekaClient {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 8888);

                OutputStream outputStream = socket.getOutputStream();

                HeartBeat heartBeat = new HeartBeat();
                heartBeat.setIp("192.56.88.199");
                heartBeat.setPort(8888);
                heartBeat.setAppName("order-META-INF.META-INF.META-INF.");
                heartBeat.setInstanceId(UUID.randomUUID().toString());

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                String s = objectMapper.writeValueAsString(heartBeat);
                outputStream.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        },1000,5000, TimeUnit.MILLISECONDS);

    }
}
