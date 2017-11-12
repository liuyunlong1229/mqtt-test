package com.lyl.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.ScheduledExecutorService;

/**
 * 订阅消息
 */
public class Subscriber {
    private String host = "tcp://192.168.206.100:1883";
    private String userName = "admin";
    private String passWord = "public";

    private MqttClient client;
    private String myTopic = "test/topic";
    private MqttConnectOptions options;
    private ScheduledExecutorService scheduler;

    public Subscriber() {
        init();
    }

    private void init() {
        try {
            client = new MqttClient(host, "consumer-1", new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(userName);
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            //设置回调
            client.setCallback(new MqttCallback() {
                
                public void connectionLost(Throwable cause) {
                    //连接丢失后，一般在这里面进行重连
                    System.out.println("connectionLost----------");
                    cause.printStackTrace();
                    while(!client.isConnected()){
                        try{
                            Thread.sleep(1000);
                            client.connect(options);
                            client.subscribe(myTopic + "/notice/",0);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish后会执行到这里
                    System.out.println("deliveryComplete---------"
                            + token.isComplete());
                }

                
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    //subscribe后得到的消息会执行到这里面
                  
                    
                    byte[] bytes=message.getPayload();
                    
                    System.out.println("messageArrived 11111111111----------" +new String(bytes)) ;
                }
            });
            client.connect(options);
            client.subscribe(myTopic + "/+/",0);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Subscriber subscriber = new Subscriber();
    }
}
