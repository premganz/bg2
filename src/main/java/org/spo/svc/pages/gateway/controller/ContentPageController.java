package org.spo.svc.pages.gateway.controller;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.spo.svc.pages.gateway.model.PostContent;
import org.spo.svc.pages.gateway.model.QMessage;
import org.spo.svc.trx.pgs.cmd.PG01O;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
public class ContentPageController {
	
	
	 @RequestMapping(value = "/entry", method = RequestMethod.GET)
	 public String home(Locale locale, Model model) {
		 PostContent content1 = new PostContent();
		 content1.setHtmlContent("hello");
		 model.addAttribute("content", content1);

		 return "x_content";
	 }
	
	
	@RequestMapping(value="/contentSubmit")
	public String processContent(
	        final PostContent content, final BindingResult bindingResult, final ModelMap model) {
	    if (bindingResult.hasErrors()) {
	        return "seedstartermng";
	    }
	    System.out.println(content.getHtmlContent());
	   // this.seedStarterService.add(seedStarter);
	    model.clear();
	    return "redirect:/seedstartermng";
	}
	
}
