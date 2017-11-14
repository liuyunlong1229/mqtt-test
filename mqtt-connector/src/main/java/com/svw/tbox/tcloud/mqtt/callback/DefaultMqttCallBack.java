package com.svw.tbox.tcloud.mqtt.callback;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultMqttCallBack  implements  MqttCallback{

	private static final Logger LOGGER=LoggerFactory.getLogger(DefaultMqttCallBack.class);

	
	IMqttClient client;
	
	String topic;

	private MqttConnectOptions  mqttConnectOptions;
	

	@Override
	public void connectionLost(Throwable cause) {
		while(!client.isConnected()) {
			try {
				client.connect(mqttConnectOptions);
				client.subscribe(topic);
				Thread.sleep(2000);
			} catch (InterruptedException | MqttException e) {
				LOGGER.error("重新连接发送异常",e);
			}
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		
	}

	public IMqttClient getClient() {
		return client;
	}

	public void setClient(IMqttClient client) {
		this.client = client;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}


	public MqttConnectOptions getMqttConnectOptions() {
		return mqttConnectOptions;
	}

	public void setMqttConnectOptions(MqttConnectOptions mqttConnectOptions) {
		this.mqttConnectOptions = mqttConnectOptions;
	}
}
