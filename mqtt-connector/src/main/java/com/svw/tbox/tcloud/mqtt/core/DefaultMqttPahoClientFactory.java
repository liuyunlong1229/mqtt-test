package com.svw.tbox.tcloud.mqtt.core;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * <p>ClassName: DefaultMqttPahoClientFactory</p>
 * <p>Description: 默认的消息客户端工厂</p>
 * <p>Author: liuyunlong</p>
 * <p>Date: 2017年11月14日</p>
 */

@Configuration
@EnableConfigurationProperties({MqttConf.class})
public class DefaultMqttPahoClientFactory implements MqttPahoClientFactory {

	@Autowired
	private MqttConf mqttConf;
	
	@Override
	public IMqttClient getClientInstance(String uri,String clientId) throws MqttException {
		return new MqttClient(uri == null ? mqttConf.getServerURIs()[0] : uri, clientId, new MemoryPersistence());
	}

	@Override
	public IMqttAsyncClient getAsyncClientInstance(String uri,String clientId) throws MqttException {
		return new MqttAsyncClient(uri == null ? mqttConf.getServerURIs()[0] : uri, clientId,new MemoryPersistence());
	}


	@Override
	public MqttConnectOptions getConnectionOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		if (this.getMqttConf().getCleanSession() != null) {
			options.setCleanSession(this.getMqttConf().getCleanSession());
		}
		if (this.getMqttConf().getConnectionTimeout() != null) {
			options.setConnectionTimeout(this.getMqttConf().getConnectionTimeout());
		}
		if (this.getMqttConf().getKeepAliveInterval() != null) {
			options.setKeepAliveInterval(this.getMqttConf().getKeepAliveInterval());
		}
		if (this.getMqttConf().getPassword() != null) {
			options.setPassword(this.getMqttConf().getPassword().toCharArray());
		}
		if (this.getMqttConf().getSocketFactory() != null) {
			options.setSocketFactory(this.getMqttConf().getSocketFactory());
		}
		if (this.getMqttConf().getSslProperties() != null) {
			options.setSSLProperties(this.getMqttConf().getSslProperties());
		}
		if (this.getMqttConf().getUserName() != null) {
			options.setUserName(this.getMqttConf().getUserName());
		}
		if (this.getMqttConf().getServerURIs() != null) {
			options.setServerURIs(this.getMqttConf().getServerURIs());
		}
		return options;
	}

	public MqttConf getMqttConf() {
		return mqttConf;
	}

	public void setMqttConf(MqttConf mqttConf) {
		this.mqttConf = mqttConf;
	}
	
}