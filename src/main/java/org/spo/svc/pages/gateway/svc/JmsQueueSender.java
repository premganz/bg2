package org.spo.svc.pages.gateway.svc;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
@Component
public class JmsQueueSender {
	@Autowired
    private JmsTemplate jmsTemplate;
    private Queue queue = new ActiveMQQueue("MAIN.2");;

    
    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}


	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}


	public Queue getQueue() {
		return queue;
	}


	public void setQueue(Queue queue) {
		this.queue = queue;
	}


	public TextMessage simpleSend(final String message1) { 		
        this.jmsTemplate.send(this.queue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message message= session.createTextMessage(message1);
                Destination tempDest = session.createQueue("MAIN.3");
               message.setJMSReplyTo(tempDest);                
               return message;
               
            }
        });
        TextMessage receivedMessage=(TextMessage)jmsTemplate.receive("MAIN.3");        
        return receivedMessage;
    }
	
}