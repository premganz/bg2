package org.spo.svc.pages.gateway.model;

import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QMessage {
	private String repo;
	private String handler;
	private String fileName;
	private String meta;
	
	public String getMeta() {
		return meta;
	}
	@XmlElement
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getHandler() {
		return handler;
	}
	@XmlElement
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getFileName() {
		return fileName;
	}
	@XmlElement
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRepo() {
		return repo;
	}
	@XmlElement
	public void setRepo(String repo) {
		this.repo = repo;
	}

@Override
public String toString() {
	Writer writer = new StringWriter();
	try {


		JAXBContext jaxbContext = JAXBContext.newInstance(QMessage.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(this, writer);


	} catch (JAXBException e) {
		e.printStackTrace();
	}
	return writer.toString();
}	
	
}
