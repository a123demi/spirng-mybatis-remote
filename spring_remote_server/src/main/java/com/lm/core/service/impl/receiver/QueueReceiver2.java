package com.lm.core.service.impl.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class QueueReceiver2 implements MessageListener {
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver2接收到消息:"
					+ ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}