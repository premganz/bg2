package org.spo.svc.pages.gateway.svc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.spo.svc.pages.gateway.model.QMessage;
import org.spo.svc.pages.thread.exch.ThreadExchanger;
import org.springframework.stereotype.Component;
@Component
public class SocketConnector {

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
		
		jaxbContext = JAXBContext.newInstance(QMessage.class);
		}catch(Exception e){
			
		}
	}
	public synchronized String getResponse( QMessage domainMessage) throws Exception{
		Writer writer = new StringWriter();
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(domainMessage, writer);
		domainRequest=writer.toString();
		
		  InetAddress host = InetAddress.getLocalHost();
	        Socket socket = null;
	        ObjectOutputStream oos = null;
	        ObjectInputStream ois = null;
	       
	            //establish socket connection to server
	            socket = new Socket(host.getHostName(), 8087);
	            //write to socket using ObjectOutputStream
	            oos = new ObjectOutputStream(socket.getOutputStream());
	           
	           oos.writeObject(domainRequest);
	           
	            //read the server response message
	            ois = new ObjectInputStream(socket.getInputStream());
	            replyMessageText = (String) ois.readObject();
	            System.out.println("Message: " + replyMessageText);
	            //close resources
	            ois.close();
	            oos.close();
	         socket.close();
	    

		System.out.println("Just said hello to:" + replyMessageText);

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
