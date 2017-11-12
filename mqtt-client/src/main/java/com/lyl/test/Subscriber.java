package com.lyl.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.ScheduledExecutorService;

/**
 * ������Ϣ
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
            // ���ó�ʱʱ�� ��λΪ��
            options.setConnectionTimeout(10);
            // ���ûỰ����ʱ�� ��λΪ�� ��������ÿ��1.5*20���ʱ����ͻ��˷��͸���Ϣ�жϿͻ����Ƿ����ߣ������������û�������Ļ���
            options.setKeepAliveInterval(20);
            //���ûص�
            client.setCallback(new MqttCallback() {
                
                public void connectionLost(Throwable cause) {
                    //���Ӷ�ʧ��һ�����������������
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
                    //publish���ִ�е�����
                    System.out.println("deliveryComplete---------"
                            + token.isComplete());
                }

                
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    //subscribe��õ�����Ϣ��ִ�е�������
                  
                    
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
