package com.example.demo.factory;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="mqtt")
public class MQttConf {

    private boolean cleanSession;
    
    private String userName;
    
    private  String  password;
   
    // 设置超时时间 单位为秒
    private int onnectionTimeout;
    // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
    private int  keepAliveInterval;
    
    private String [] serverURIs;
    
    private String host;
    
    
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String[] getServerURIs() {
		return serverURIs;
	}
	public void setServerURIs(String[] serverURIs) {
		this.serverURIs = serverURIs;
	}
	public boolean isCleanSession() {
		return cleanSession;
	}
	public void setCleanSession(boolean cleanSession) {
		this.cleanSession = cleanSession;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getOnnectionTimeout() {
		return onnectionTimeout;
	}
	public void setOnnectionTimeout(int onnectionTimeout) {
		this.onnectionTimeout = onnectionTimeout;
	}
	public int getKeepAliveInterval() {
		return keepAliveInterval;
	}
	public void setKeepAliveInterval(int keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
	}
    
    
}
