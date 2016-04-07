package org.spo.ifs2.dsl.model;

import org.spo.ifs2.dsl.controller.NavEvent;
import org.spo.ifs2.dsl.controller.TrxInfo;

public abstract class AbstractTask {

	public abstract NavEvent  initView(TrxInfo info);
	public abstract NavEvent initTask(String dataId, TrxInfo info) throws Exception;
	//public abstract NavEvent initView(String dataId, ModelMap info);
	public abstract NavEvent processViewEvent(String event, TrxInfo info);	
	public abstract String processAjaxEvent(String event, TrxInfo info);
	
	
	
	
}

