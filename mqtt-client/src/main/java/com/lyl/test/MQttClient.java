package com.lyl.test;

	import java.net.URISyntaxException;  
	  
	import org.fusesource.mqtt.client.Future;  
	import org.fusesource.mqtt.client.FutureConnection;  
	import org.fusesource.mqtt.client.MQTT;  
	import org.fusesource.mqtt.client.Message;  
	import org.fusesource.mqtt.client.QoS;  
	import org.fusesource.mqtt.client.Topic;  
	  
	/** 
	 * �ͻ��˶�����Ϣ 
	 */  
	public class MQttClient {  
	      
	    private final static String CONNECTION_STRING = "tcp://192.168.206.100:1883";    
	    private final static boolean CLEAN_START = true;    
	    private final static short KEEP_ALIVE = 30;// �ͺ����磬��������Ҫ��ʱ��ȡ���ݣ�����30s    
	    private final static String CLIENT_ID = "client";    
	    public static Topic[] topics = {     
	        new Topic("mqtt/aaa", QoS.EXACTLY_ONCE), //  2 ֻ��һ��  
	        new Topic("mqtt/bbb", QoS.AT_LEAST_ONCE),  // 1 ����һ��  
	        new Topic("mqtt/ccc", QoS.AT_MOST_ONCE) };  // 0 ����һ��  
	        
	    public final static long RECONNECTION_ATTEMPT_MAX = 6;    
	    public final static long RECONNECTION_DELAY = 2000;    
	    
	    public final static int SEND_BUFFER_SIZE = 64 ;// ������󻺳�Ϊ2M    
	  
	    public static void main(String[] args) {  
	          // ����MQTT����    
	        MQTT mqtt = new MQTT();    
	        try {    
	            // ����mqtt broker��ip�Ͷ˿�    
	            mqtt.setHost(CONNECTION_STRING);    
	            // ����ǰ��ջỰ��Ϣ    
	            mqtt.setCleanSession(CLEAN_START);    
	            // �����������ӵĴ���    
	            mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);    
	            // ���������ļ��ʱ��    
	            mqtt.setReconnectDelay(RECONNECTION_DELAY);    
	            // ��������ʱ��    
	            mqtt.setKeepAlive(KEEP_ALIVE);    
	            // ���û���Ĵ�С    
	            mqtt.setSendBufferSize(SEND_BUFFER_SIZE);    
	            //���ÿͻ���id    
	            mqtt.setClientId(CLIENT_ID); 
	            
	            
	            //mqtt.setUserName("admin");
	            
	            //mqtt.setPassword("public");
	    
	            // ��ȡmqtt�����Ӷ���BlockingConnection  ,����Futureģʽ ��������  
	            
	            
	            final FutureConnection connection = mqtt.futureConnection();
	            
	            connection.isConnected();
	            
	            connection.connect();    
	            connection.subscribe(topics);    
	            while (true) {    
	                Future<Message> futrueMessage = connection.receive();    
	                Message message = futrueMessage.await();      
	                System.out.println("���Ѷ˽��յ� Message " + "Topic Title :" + message.getTopic() + " context :"    
	                        + String.valueOf(message.getPayloadBuffer()));    
	            }    
	        } catch (URISyntaxException e) {    
	            e.printStackTrace();    
	        } catch (Exception e) {    
	            e.printStackTrace();    
	        } finally {    
	        }    
	    }  
	  

}
