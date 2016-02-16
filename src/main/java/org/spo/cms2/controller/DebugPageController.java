package org.spo.cms2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DebugPageController {
	
	@RequestMapping(value="/admin/debug", method=RequestMethod.GET)
	public String handlePageRequest_String( ) {
			return "debug";
		
	}
	
	
}
