package com.lm.core.service.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class TopicReceiver2 implements MessageListener {
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver2 client:接收到消息:"
					+ ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}