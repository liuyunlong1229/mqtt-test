package com.example.demo.factory;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class  MqttClientFactory {
	
	private MQttConf defaultConf;
	
	
	public MqttClient getClientInstance(String clientId,MQttConf mqttConf) throws MqttException {
		
			 MqttConnectOptions options = new MqttConnectOptions();
	         options.setCleanSession(mqttConf.isCleanSession());
	         options.setUserName(mqttConf.getUserName());
	         options.setPassword(mqttConf.getPassword().toCharArray());
	         options.setConnectionTimeout(mqttConf.getOnnectionTimeout());
	         options.setServerURIs(mqttConf.getServerURIs());
	         options.setKeepAliveInterval(mqttConf.getKeepAliveInterval());
	         MqttClient   client = new MqttClient(mqttConf.getHost(), clientId, new MemoryPersistence());
         
	         if(!client.isConnected()) {
	        	 client.connect(options);
	         }
             return client;
		
	}
	
	
	public MqttClient getDefaultClient(String clientId) throws MqttException {
		return getClientInstance(clientId,defaultConf);
	}
	
}
