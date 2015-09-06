package org.spo.svc.pages.gateway.svc;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.TimeLimitExceededException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.spo.svc.pages.gateway.model.QMessage;
import org.spo.svc.pages.thread.exch.ThreadExchanger;
import org.spo.svc.shared.TestResourceException;
import org.springframework.stereotype.Component;
@Component
public class MQConnector {

	private ThreadExchanger exchanger=ThreadExchanger.instance();
	private static final String URL="tcp://localhost:61616";
	private String response;
	
	String domainRequest= "";
	String replyMessageText= "";		
	
	
	// Create a ConnectionFactory
	ActiveMQConnectionFactory connectionFactoryIN = new ActiveMQConnectionFactory("tcp://localhost:61616");
	Connection connectionIn ;
	
	Session session ;
	Destination destinationIn ;
	MessageConsumer consumer ;

	ActiveMQConnectionFactory connectionFactoryOut = new ActiveMQConnectionFactory("tcp://localhost:61616");
	Connection connectionOut ;	
	Session sessionOut ;
	Destination destinationOut ;
	MessageProducer reqProducer ;
	JAXBContext jaxbContext ;
	
	{
		try{
		connectionFactoryIN = new ActiveMQConnectionFactory("tcp://localhost:61616");
		connectionIn = connectionFactoryIN.createConnection();
		connectionIn.start();
		session = connectionIn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destinationIn = session.createQueue("MAIN.3");
		consumer = session.createConsumer(destinationIn);

		connectionFactoryOut = new ActiveMQConnectionFactory("tcp://localhost:61616");
		connectionOut = connectionFactoryOut.createConnection();
		connectionOut.start();
		sessionOut = connectionOut.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destinationOut = sessionOut.createQueue("MAIN.2");
		reqProducer = sessionOut.createProducer(null);
		reqProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		jaxbContext = JAXBContext.newInstance(QMessage.class);
		}catch(Exception e){
			
		}
	}
	public String getResponse( QMessage domainMessage) throws Exception{
		Writer writer = new StringWriter();
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(domainMessage, writer);
		domainRequest=writer.toString();

			TextMessage requestMessage =null;

			try {
				
				requestMessage = sessionOut.createTextMessage(domainRequest);
				reqProducer.send(destinationOut, requestMessage);
				Message responseMessage = consumer.receive(500);
				if(responseMessage instanceof TextMessage && ((TextMessage)responseMessage).getText()!=null) {
					TextMessage txtMsg = (TextMessage) responseMessage;
					replyMessageText= txtMsg.getText();
				}


			}catch (Exception e) {
				System.out.println("Caught: " + e);
				e.printStackTrace();
								
			}finally{
				try
				{

				}
				catch (Throwable e)
				{
					// Swallow
				}
			}

			return replyMessageText;


	
		
	}
	public List<String> getResponseAsList( QMessage domainMessage) throws Exception{
		String response = getResponse(domainMessage);
		//String[] array = response.split("\\*\\*\\*EOL\\*\\*\\*");
		//String[] array = response.split("[\r\n]");
		String[] array = response.split("</br>");
		return Arrays.asList(array);
	}

	public String pollResponse() {

		for(int i=0;i<5;i++){			
			response=exchanger.getResponse();
			if(!response.isEmpty()){
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
