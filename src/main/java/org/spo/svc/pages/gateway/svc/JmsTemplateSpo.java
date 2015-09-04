package org.spo.svc.pages.gateway.svc;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

//@Service
public class JmsTemplateSpo  {
//
//	
//    @Bean
//    public JmsTemplate sendRequest() {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setDefaultDestination(new ActiveMQQueue("MAIN.2"));
//        jmsTemplate.setConnectionFactory(connectionFactory());
//        jmsTemplate.sendAndReceive("", messageCreator)
//        return jmsTemplate;
//    }
//
//    public Destination getDestination(){
//    	Destination destination = new Destination();
//    }
//    
//    
//    @Bean
//    public ActiveMQConnectionFactory connectionFactory() {
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
//        return activeMQConnectionFactory;
//    }
//    
//    

}