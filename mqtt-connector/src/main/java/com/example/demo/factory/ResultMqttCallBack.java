package com.example.demo.factory;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ResultMqttCallBack<T> implements MqttCallback{
	
	private T  t;
	
	MQttMessageConvertor<T> convertor;
	
	@Override
	public void connectionLost(Throwable cause) {
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		t=convertor.converte(message);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		
	}
	
	
	
	public T  getResult() throws InterruptedException {
		
		if(t==null) {
			wait();
		}else {
			notify();
		}
		return t;
	}

}
