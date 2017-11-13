package com.lyl.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Date;

/**
 *  发布消息
 */
public class Publisher {
    private MqttClient client;
    //private String host = "tcp://192.168.206.100:1883";
    
    private String host="tcp://10.122.7.111:1883"; //公司主机地址
    
    private String userName = "admin";
    private String passWord = "public";
    private MqttTopic topic;
    private MqttMessage message;
    private String myTopic = "test";

    public Publisher(){
        try{
            client = new MqttClient(host, "provider-1", new MemoryPersistence());
            connect();
        }catch (Exception e){
            e.printStackTrace();
        }
//        delivery();
    }

    private void delivery(){
        try {
            MqttDeliveryToken token = topic.publish(message);
            token.waitForCompletion();
            System.out.println(token.isComplete() + "=========");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost-----------");
                    while(!client.isConnected()){
                        try{
                            Thread.sleep(1000);
                            client.connect(options);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //System.out.println("deliveryComplete---------"+token.isComplete());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message)
                        throws Exception {
                    //System.out.println("messageArrived----------"+topic+"-----" + message.getPayload());
                }
            });
//            topic = client.getTopic(myTopic);
//            message = new MqttMessage();
//            message.setQos(1);
//            message.setRetained(true);
//            System.out.println(message.isRetained()+"------ratained状态");
//            message.setPayload("eeeeeaaaaaawwwwww---".getBytes());
//            client.connect(options);
            client.connect(options);
            int i=1;
            while(true){
            	if(i%10==0) 
            	{
            		Thread.sleep(1000*10);
            	}
                try{
                	
                    String content ="message"+i;
                	// new Date() + "MQTT Test body" + i;
                    MqttMessage message = new MqttMessage(content.getBytes());
                    message.setQos(0);
                    System.out.println("发送消息:>>>>" + content);
                    client.publish(myTopic, message);
                }catch (Exception e){
                    e.printStackTrace();
                }
                
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Publisher client = new Publisher();
    }
}