package com.svw.tbox.tcloud.mqtt.convertor;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttMessageConvertor<T> {
	
	public T converte(MqttMessage message);
}
