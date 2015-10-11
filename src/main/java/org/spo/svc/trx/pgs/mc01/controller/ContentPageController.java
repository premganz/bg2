package org.spo.svc.trx.pgs.mc01.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.svc.pages.gateway.model.PostContent;
import org.spo.svc.pages.gateway.model.QMessage;
import org.spo.svc.pages.gateway.svc.SocketConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ContentPageController {

    private static final Logger logger = LoggerFactory
            .getLogger(ContentPageController.class);

   
 
    
	private SocketConnector connector=new SocketConnector();
	
	
	 @RequestMapping(value="/post",   params={"fileName"})
	 public String fetchPost(
		        final PostContent content, final BindingResult bindingResult, final ModelMap model,
		        @RequestParam(value="fileName", required=false) String metaValue) {
		    if (bindingResult.hasErrors()) {
		        return "seedstartermng";
		    }
		    
		    System.out.println(content.getHtmlContent());
		   // this.seedStarterService.add(seedStarter);
		    
		  
		        logger.info("Searching "+metaValue  );
		      
		        
		        QMessage message = new QMessage();
				message.setHandler("fetch");				
				message.setMeta(metaValue);
				String response ="<p>blank reply</p>";
				try {		
					response = connector.getResponse(message);
					//TextMessage reply = sender.simpleSend(message.toString()); 
					//response=reply.getText();
					content.setHtmlContent(response);
				} catch (Exception e) {			
					e.printStackTrace();
				}
			
			response=response.equals("")?"<p>blank reply</p>":response;
		    model.clear();
		    model.addAttribute("message", content);
		    return "post" ;
		}
	 
	 
	
	
}
