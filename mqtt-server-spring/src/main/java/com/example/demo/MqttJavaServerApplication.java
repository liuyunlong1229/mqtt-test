package com.example.demo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
@IntegrationComponentScan
public class MqttJavaServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
        		new SpringApplicationBuilder(MqttJavaServerApplication.class)
        				.web(false)
        				.run(args);
        MyGateway gateway = context.getBean(MyGateway.class);
        
      /*  int i=1;
        while(true) {
        	if(i %10==0) {
        		try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}*/
        //}
        	
        	for(int i=1;i<=100;i++) {
        		gateway.sendToMqtt("开始第【"+i+"】次发送数据"+new SimpleDateFormat("yyyyMMHHmmssSSS").format(new Date()));
    		}
        
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        //factory.setServerURIs("tcp://host1:1883", "tcp://host2:1883");
        factory.setServerURIs("tcp://192.168.206.100:1883");
        factory.setUserName("adimin");
        factory.setPassword("public");
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                       new MqttPahoMessageHandler("testServer", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("testTopic");
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MyGateway {

        void sendToMqtt(String data);

    }

}