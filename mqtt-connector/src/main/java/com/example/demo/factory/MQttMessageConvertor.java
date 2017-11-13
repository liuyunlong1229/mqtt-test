package com.example.demo.factory;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface  MQttMessageConvertor<T> {
	 
	T converte(MqttMessage message);
	

}
