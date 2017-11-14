package com.svw.tbox.tcloud.mqtt.core;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svw.tbox.tcloud.mqtt.callback.DefaultMqttCallBack;

@Component
public class MqttSubscriber {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(MqttSubscriber.class);
	
	@Autowired
	MqttPahoClientFactory mqttPahoClientFactory;

	public void subscribe(String clientId,String topic, DefaultMqttCallBack callBack) {

		IMqttClient client;
		try {
			client = mqttPahoClientFactory.getClientInstance(null,clientId);
			client.connect(mqttPahoClientFactory.getConnectionOptions());
			client.subscribe(topic);
			callBack.setClient(client);
			callBack.setMqttConnectOptions(mqttPahoClientFactory.getConnectionOptions());
			callBack.setTopic(topic);
			client.setCallback(callBack);

		} catch (MqttException e) {
			LOGGER.error("clientId:{} 订阅 topic:{}发生异常",clientId,topic,e);
		}

	}
	

}
