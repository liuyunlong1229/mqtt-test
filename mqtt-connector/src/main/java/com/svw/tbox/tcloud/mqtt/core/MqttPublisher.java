package com.svw.tbox.tcloud.mqtt.core;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MqttPublisher {

	private static final Logger LOGGER=LoggerFactory.getLogger(MqttPublisher.class);

	@Autowired
	MqttPahoClientFactory mqttPahoClientFactory;
	
	
	public void publish(String clientId,String topic, String content) {
		IMqttClient client=null;
			try {
				client = mqttPahoClientFactory.getClientInstance(null,clientId);
				client.connect(mqttPahoClientFactory.getConnectionOptions());
				MqttMessage mm=new MqttMessage(content.getBytes("utf-8"));
				client.publish(topic, mm);
				
		} catch (MqttException | UnsupportedEncodingException e) {
			LOGGER.error("发布消息发生异常",e);
		}
	}

}
