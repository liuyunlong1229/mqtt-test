package com.svw.tbox.tcloud.mqtt.convertor;



import org.eclipse.paho.client.mqttv3.MqttMessage;


public class  DefaultMqttMessageConvertor implements MqttMessageConvertor<String> {

	@Override
	public String converte(MqttMessage message) {
		return new String(message.getPayload());
	}
	 

}
