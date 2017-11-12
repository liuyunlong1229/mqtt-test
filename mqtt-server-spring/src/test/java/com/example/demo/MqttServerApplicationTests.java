package com.example.demo;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqttServerApplicationTests {

	@Resource
	private MqttPahoMessageHandler mqtt;

	@Test
	public void contextLoads() {
		int i=1;
		while(true) {
			
			if(i %10==0) {
				
				try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Message<String> message = MessageBuilder.withPayload("==========1111111111111111111111111=========")
					.setHeader(MqttHeaders.TOPIC, "robot_server").build();
			mqtt.handleMessage(message);
			System.out.println("成功");
		}
		
	}

}
