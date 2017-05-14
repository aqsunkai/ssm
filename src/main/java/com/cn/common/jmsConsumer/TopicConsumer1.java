package com.cn.common.jmsConsumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;
@Component
public class TopicConsumer1 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage text = null;
			if(message instanceof TextMessage){
				text = (TextMessage) message;
				System.out.println("TopicConsumer1接收到消息："+text.getText());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
