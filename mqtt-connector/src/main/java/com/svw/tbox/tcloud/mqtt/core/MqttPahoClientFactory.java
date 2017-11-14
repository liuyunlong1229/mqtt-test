package com.svw.tbox.tcloud.mqtt.core;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface MqttPahoClientFactory {
 
	
	/**
	 * Retrieve a client instance.
	 *
	 * @param clientId The client id.
	 * @return The client instance.
	 * @throws MqttException Any.
	 */
	public IMqttClient getClientInstance(String uri,String clientId) throws MqttException;

	
	/**
	 * Retrieve the connection options.
	 *
	 * @return The options.
	 */
	MqttConnectOptions getConnectionOptions();

}
