package com.svw.tbox.tcloud.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.svw.tbox.tcloud.mqtt.core.MqttPublisher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="测试MQTT API")
public class TestMqttController {
	
	@Autowired
	private MqttPublisher mqttPublisher;
	
	@ApiOperation("测试发送消息")
	@RequestMapping(value="/sendMessage",method=RequestMethod.POST)
	@ApiImplicitParam(name="message",value="消息内容",paramType="query",required=true)
	public void testSend(String message) {
	mqttPublisher.publish("001","test", message);
		
	}

}
