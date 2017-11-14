package com.svw.tbox.tcloud.mqtt.core;

import java.util.Properties;
import javax.net.SocketFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@ConfigurationProperties(prefix = "mqtt.conf.options")
public class MqttConf {
	
	
	private volatile Boolean cleanSession;

	private volatile Integer connectionTimeout;

	private volatile Integer keepAliveInterval;

	private volatile String password;

	private volatile SocketFactory socketFactory;

	private volatile Properties sslProperties;

	private volatile String userName;


	private volatile String[] serverURIs;


	public Boolean getCleanSession() {
		return cleanSession;
	}


	public void setCleanSession(Boolean cleanSession) {
		this.cleanSession = cleanSession;
	}


	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}


	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}


	public Integer getKeepAliveInterval() {
		return keepAliveInterval;
	}


	public void setKeepAliveInterval(Integer keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public SocketFactory getSocketFactory() {
		return socketFactory;
	}


	public void setSocketFactory(SocketFactory socketFactory) {
		this.socketFactory = socketFactory;
	}


	public Properties getSslProperties() {
		return sslProperties;
	}


	public void setSslProperties(Properties sslProperties) {
		this.sslProperties = sslProperties;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String[] getServerURIs() {
		return serverURIs;
	}


	public void setServerURIs(String[] serverURIs) {
		Assert.notNull(serverURIs, "'serverURIs' must not be null.");
		this.serverURIs = serverURIs;
	}
}
