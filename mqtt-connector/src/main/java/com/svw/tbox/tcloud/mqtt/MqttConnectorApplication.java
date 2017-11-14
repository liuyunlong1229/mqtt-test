package com.svw.tbox.tcloud.mqtt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.svw.tbox.tcloud.mqtt.callback.TestMqttCallBack;
import com.svw.tbox.tcloud.mqtt.core.MqttSubscriber;




@SpringBootApplication
public class MqttConnectorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =new SpringApplicationBuilder(MqttConnectorApplication.class).run(args);
		final MqttSubscriber mqttSubscriber = context.getBean(MqttSubscriber.class);
		mqttSubscriber.subscribe("002", "test", new TestMqttCallBack());
		
		
	}

	
}
