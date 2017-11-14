package com.svw.tbox.tcloud.mqtt.callback;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.svw.tbox.tcloud.mqtt.convertor.MqttMessageConvertor;

public class ResultMqttCallBack<T> extends DefaultMqttCallBack {

	private MqttMessageConvertor<T> convertor;

	private Object resultReadLock = new Object();

	private T t;

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		t = convertor.converte(message);
	}

	public T getResult() throws InterruptedException {
		synchronized (resultReadLock) {
			while (t == null) {
				resultReadLock.wait();
			}
			resultReadLock.notifyAll();
			return t;
		}
	}

}
