package org.spo.svc.pages.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin/debug")
public class DebugPageController {

	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String handlePageRequest_String( ) {
			return "debug";
		
	}
	
	
}
