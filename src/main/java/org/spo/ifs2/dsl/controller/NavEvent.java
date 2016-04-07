package org.spo.ifs2.dsl.controller;

import org.spo.ifs2.dsl.controller.DSLConstants.EventType;

public class NavEvent {

	private DSLConstants.EventType eventType;
	private String trxId;//target trx
	private String eventId;//target event mostly for triggering an event from the page.
	private String taskId;//target task
	public String dataId;//data key for the trx
	public NavEvent previousEvent;
	public String url;

	
	public EventType getEventType(){
		return this.eventType;
	}
	public String getEventId(){
		return this.eventId;
	}
	public String getTrxId(){
		return this.trxId;
	}
	public NavEvent(DSLConstants.EventType eventType, String trxId,String taskId,String eventId,  String dataId){
		this.eventType=eventType;
		this.trxId=trxId;
		this.taskId=taskId;
		this.eventId=eventId;
		this.dataId=dataId;
	}
	public NavEvent(DSLConstants.EventType eventType){
		this.eventType=eventType;
	}
	
	public static NavEvent create(DSLConstants.EventType type, String param){
		NavEvent event = new NavEvent(type);
		if((type.equals(DSLConstants.EventType.REFRESHPAGE)||type.equals(DSLConstants.EventType.TASKSET))){
			event.taskId=param;	
		}else if(type.equals(DSLConstants.EventType.PROCESSEVENT)){
			event.eventId=param;
		}else{
			event.trxId=param;
		}
		return event;		
	}
	public static NavEvent create(DSLConstants.EventType type){
		NavEvent event = new NavEvent(type);
		return event;		
	}
	public String getRedirect(){return "";}


}
