package com.svw.tbox.tcloud.mqtt.callback;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMqttCallBack extends DefaultMqttCallBack {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(TestMqttCallBack.class);

	
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		String content=new String(message.getPayload(),"utf-8");
		LOGGER.info("收到主题[{}]消息>>>>>>{}",topic,content);
	}

}
