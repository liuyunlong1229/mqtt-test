package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@SpringBootApplication
public class MqttJavaClientApplication {

    public static void main(String[] args) {
    	new SpringApplicationBuilder(MqttJavaClientApplication.class)
    			.web(false)
    			.run(args);
    }

    @Bean
    public MessageChannel mqttInputChannel() {
    	return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
    	MqttPahoMessageDrivenChannelAdapter adapter =
    			new MqttPahoMessageDrivenChannelAdapter("tcp://10.122.7.111:1883", "testClient",
    			                                 "topic1", "topic2");
    	adapter.setCompletionTimeout(5000);
    	adapter.setConverter(new DefaultPahoMessageConverter());
    	adapter.setQos(1);
    	adapter.setOutputChannel(mqttInputChannel());
    	return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
    	return new MessageHandler() {
    		int i=1;
    		@Override
    		public void handleMessage(Message<?> message) throws MessagingException {
    			i++;
    			System.out.println("第"+i+"次接收到消息内容为：<<<<<<<<"+message.getPayload());
    		}

    	};
    }

}