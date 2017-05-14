package com.cn.common.jmsProducer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
@Component
public class TopicProducer {

	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate; //注入配置文件中的bean
	
	@Autowired
	@Qualifier("topicDestination")
    private Destination destination;
	
	public void send(final String message){
		/*jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session paramSession) throws JMSException {
				return paramSession.createTextMessage(message);
			}
		});*/
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
}
