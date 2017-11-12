package com.lyl.test;

	import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.FutureConnection;  
	import org.fusesource.mqtt.client.MQTT;  
	import org.fusesource.mqtt.client.QoS;  
	import org.fusesource.mqtt.client.Topic;  
	  
	/** 
	 * ����˷�����Ϣ 
	 */  
	public class MQttServer {  
	  
	    private final static String CONNECTION_STRING = "tcp://192.168.206.100:1883";  
	    private final static boolean CLEAN_START = true;  
	    private final static String CLIENT_ID = "server";  
	    private final static short KEEP_ALIVE = 30;// �ͺ����磬��������Ҫ��ʱ��ȡ���ݣ�����30s  
	  
	    public static Topic[] topics = {   
	    		   
	    			        new Topic("mqtt/aaa", QoS.EXACTLY_ONCE), //  2 ֻ��һ��  
	    			        new Topic("mqtt/bbb", QoS.AT_LEAST_ONCE),  // 1 ����һ��  
	    			        new Topic("mqtt/ccc", QoS.AT_MOST_ONCE) };  // 0 ����һ��  
	  
	    public final static long RECONNECTION_ATTEMPT_MAX = 6;  
	    public final static long RECONNECTION_DELAY = 2000;  
	  
	    public final static int SEND_BUFFER_SIZE = 64;// ������󻺳�  
	  
	    public static void main(String[] args) {  
	         MQTT mqtt = new MQTT();      
	         try {      
	             //==MQTT����˵��    
	             //���÷���˵�ip      
	             mqtt.setHost(CONNECTION_STRING);      
	             //����ǰ��ջỰ��Ϣ ,����Ϊfalse��MQTT���������־û��ͻ��˻Ự�����嶩�ĺ�ACKλ�ã�Ĭ��Ϊtrue    
	             mqtt.setCleanSession(CLEAN_START);      
	             //��������ʱ�� ,����ͻ��˴�����Ϣ�����ʱ�������������������Ծݴ��ж���ͻ��˵������Ƿ��Ѿ��Ͽ����Ӷ�����TCP/IP��ʱ�ĳ�ʱ��ȴ�    
	             mqtt.setKeepAlive(KEEP_ALIVE);      
	             //���ÿͻ���id,�������ÿͻ��˻Ự��ID����setCleanSession(false);������ʱ��MQTT���������ø�ID�����Ӧ�ĻỰ��    
	             //��IDӦ����23���ַ���Ĭ�ϸ��ݱ�����ַ���˿ں�ʱ���Զ�����    
	             mqtt.setClientId(CLIENT_ID);    
	                 
	             //==ʧ������������˵��    
	              //�����������ӵĴ��� ,�ͻ����Ѿ����ӵ�������������ĳ��ԭ�����ӶϿ�ʱ��������Դ����������ô����ͻ��˽����ش���-1��Ϊ���������ޣ�Ĭ��Ϊ-1    
	             mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);      
	             //���������ļ��ʱ��  ,�״������Ӽ����������Ĭ��Ϊ10ms    
	             mqtt.setReconnectDelay(RECONNECTION_DELAY);      
	             //����socket���ͻ�������С��Ĭ��Ϊ65536��64k��    
	             mqtt.setSendBufferSize(SEND_BUFFER_SIZE);      
	             ////���÷������ݰ�ͷ���������ͻ���������ֶΣ�Ĭ��Ϊ8����Ϊ��������󻯴���    
	             mqtt.setTrafficClass(8);    
	                 
	             //==������������˵��    
	             mqtt.setMaxReadRate(0);//�������ӵ����������ʣ���λΪbytes/s��Ĭ��Ϊ0����������    
	             mqtt.setMaxWriteRate(0);//�������ӵ���������ʣ���λΪbytes/s��Ĭ��Ϊ0����������    
	             
	            // mqtt.setUserName("admin");
	            // mqtt.setPassword("public");
	                 
	             //ʹ��Future��������       
	             final BlockingConnection connection= mqtt.blockingConnection();      
	             connection.connect();      
	             
	         
	             int count=1;      
	             while(true){      
	                 count++;      
	                 // ���ڷ�����Ϣ��Ŀǰ�ֻ��β���Ҫ�����˷�����Ϣ      
	                 //���������      
	                 String message="Hello "+count+" MQTT...";      
	                 String topic = "mqtt/ccc";   
	                 connection.publish(topic, message.getBytes(), QoS.EXACTLY_ONCE,false);      
	                 System.out.println("����˷��͵� Message "+"Topic Title :"+topic+" context :"+message); 
	                 
	                 if(count %10==0) {
	                	 Thread.sleep(1000*40);
	                 }
	                
	             }      
	         } catch (URISyntaxException e) {      
	             e.printStackTrace();      
	         } catch (Exception e) {      
	             e.printStackTrace();      
	         }finally{  
	         }  
	    }  
	  
	}  
