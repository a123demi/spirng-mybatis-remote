package com.lm.core.service.impl.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class TopicReceiver implements MessageListener {
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver1接收到消息:"
					+ ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}