package org.spo.ifs2.dsl.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.spo.ifs2.dsl.controller.DSLConstants.EventType;
import org.spo.ifs2.dsl.controller.TrxInfo.Scope;
import org.spo.ifs2.dsl.model.AbstractTask;

public abstract class AbstractHandler {


	protected EventChain eventChain;


	/**
	 * 
	 * Use a Syntax to define states and transitions like
	 * 
	 * M0101->M0102|switch 
	 * eventChain.setGroundState(m01).addEvent("switch").addState(m02)
	 * 
	 * 
	 * 
	 */


	protected Map<String,AbstractTask> taskChannel = new LinkedHashMap<String,AbstractTask>();
	protected Map<String,NavEvent> eventMap = new LinkedHashMap<String,NavEvent>();


	public abstract void configureChannel();
	//The default landing page for the trx  is the initTask of the first task. 

	public NavEvent  handleInBound(NavEvent navevent,  TrxInfo info) throws Exception{
	
		if(navevent.getEventType().equals(EventType.TRXSWITCH)){
			info.getState().trxId=navevent.getTrxId();
			info.getState().taskId="01";
			info.getState().dataId=navevent.dataId;
			info.getState().eventId="";
			navevent=taskChannel.get("01").initTask(navevent.dataId, info);
			
		}
		if(navevent.getEventType().equals(EventType.TASKSET)){
			navevent=taskChannel.get(info.getState().taskId).initTask(navevent.dataId, info);
		}
		if(navevent.getEventType().equals(EventType.PROCESSEVENT)){
			navevent=taskChannel.get(info.getState().taskId).processViewEvent(navevent.getEventId(), info);
		}
		info.getState().lastEvent=navevent;
		return navevent;
		
	}

	
	public String handleOutBound(NavEvent navevent,HttpServletRequest request, TrxInfo info) throws Exception{
		info.getState().lastEvent=navevent;
	
				
		if(navevent.getEventType().equals(EventType.REFRESHPAGE)){
			String taskName = taskChannel.get("01").getClass().getSimpleName();				
			return info.getState().trxId.toLowerCase()+"/"+taskName;
		}else if(navevent.getEventType().equals(EventType.TASKSET)){			
			NavEvent navevent1=handleInBound(navevent, info);
			info.getState().lastEvent=navevent1;
			if(navevent1.getEventType().equals(EventType.REFRESHPAGE)){
				String taskName = taskChannel.get("01").getClass().getSimpleName();				
				return info.getState().trxId.toLowerCase()+"/"+taskName;
			}
		}else if(navevent.getEventType().equals(EventType.TRXSWITCH)){
			info.getState().trxId=navevent.getTrxId();
			String url;
			//if(request.getQueryString()!=null){
				url=request.getRequestURL().toString() + "?" + request.getQueryString();
				return "redirect:../"+info.getState().trxId+"/"+navevent.dataId;
			//}else{
			//	url=request.getRequestURL().toString();
			//}
			
					
		}else{
			
		}
		
		return "";
	}
	public String  handle1(String pageEvent, String dataId, StateInfo state, TrxInfo info, HttpServletRequest request){
		configureChannel();
		NavEvent navevent= info.getState().lastEvent;
		try{
		if(pageEvent==null){ //First load
				navevent=new NavEvent(EventType.TRXSWITCH,this.getClass().getSimpleName().replaceAll("Handler", ""),"01","",dataId );
				NavEvent returnEvent= handleInBound(navevent, info);
				return handleOutBound(returnEvent,request,info);
						
		}else{			
				navevent = NavEvent.create(EventType.PROCESSEVENT,pageEvent);		
				NavEvent returnEvent= handleInBound(navevent, info);
				return handleOutBound(returnEvent,request,info);
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	return "";	
	}
	
	// trxid/taskid/eventid/dataid
//	public String  handle(String pageEvent, String event, StateInfo state, TrxInfo info, HttpServletRequest request){
//		configureChannel();
//		NavEvent navevent=null;
//	
//		if(state.lastEvent==null){
//			//First Launch from Trx Menu
//			try{
//				navevent=taskChannel.get("01").initTask(state.dataId, info);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			
//			info.getState().taskId="01";
//			info.getState().lastEvent=navevent;
//			if(navevent.getEventType().equals(EventType.REFRESHPAGE)){
//				String taskName = taskChannel.get("01").getClass().getSimpleName();				
//				return state.trxId.toLowerCase()+"/"+taskName;
//			}
//			navevent.url=request.getRequestURL().toString() + "?" + request.getQueryString();
//
//		}else if(!state.lastEvent.eventType.equals(DSLConstants.EventType.TRXSWITCH)){			
//			
//			navevent=taskChannel.get(info.getState().taskId).processViewEvent(event, info);		
//			navevent.url=request.getRequestURL().toString() + "?" + request.getQueryString();
//			info.getState().taskId=navevent.taskId;
//			info.getState().lastEvent=navevent;			
//			if(navevent.eventType.equals(EventType.REFRESHPAGE)){
//				String taskName = taskChannel.get("01").getClass().getSimpleName();
//				return state.trxId.toLowerCase()+"/"+taskName;
//			}else if(navevent.eventType.equals(DSLConstants.EventType.TRXSWITCH)){			
//				return "redirect:../../"+navevent.trxId+"/"+navevent.dataId;
//			}else if(navevent.eventType.equals(DSLConstants.EventType.TASKSET)){
//				String taskId = navevent.taskId;
//				String dataId=navevent.dataId;
//				AbstractTask task= taskChannel.get(taskId);
//				try {
//					navevent=task.initTask(dataId, info);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				if(navevent.eventType.equals(EventType.REFRESHPAGE)){
//					String taskName = taskChannel.get("01").getClass().getSimpleName();
//					return state.trxId.toLowerCase()+"/"+taskName;
//				}else if(navevent.eventType.equals(EventType.TRXSWITCH)){
//					
//					return "redirect:../"+navevent.url.replace("/M01/", "/C01/");
//				}
//			}			
//		}else{
//			return "";
//		}
//
//		return "";
//
//	}





}
