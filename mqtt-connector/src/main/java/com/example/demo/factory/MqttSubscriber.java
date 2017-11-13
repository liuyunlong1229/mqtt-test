package com.example.demo.factory;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSubscriber<T>{
	
	
	private MqttClientFactory mqttClientFactory;
	
	
	public T  subscrib(String clientId,String topic,int qos) throws MqttException, InterruptedException {
		
		MqttClient mqttClient=mqttClientFactory.getDefaultClient(clientId);
		
	    mqttClient.subscribe(topic,qos);
        
        //设置回调
	    ResultMqttCallBack<T> cb=new ResultMqttCallBack<T>();
		
		mqttClient.setCallback(cb);
		
		return cb.getResult();
		
	}

}
