package org.spo.svc.pages.gateway.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.cms.svc.PageService;
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
public class CMSContentPageController {

    private static final Logger logger = LoggerFactory
            .getLogger(CMSContentPageController.class);

   
 
    
	private SocketConnector connector=new SocketConnector();
	
	 @RequestMapping(value = "admin/entry", method = RequestMethod.GET)
	 public String home(Locale locale, Model model) {
		 PostContent content1 = new PostContent();
		 content1.setHtmlContent("hello");
		 model.addAttribute("content", content1);

		 return "x_content";
	 }
	 
	 @RequestMapping(value = "admin/entryTemplate", method = RequestMethod.GET)
	 public String homeTemplate(Locale locale, Model model) {
		 PostContent content1 = new PostContent();
		 content1.setHtmlContent("hello");
		 model.addAttribute("content", content1);

		 return "y_content";
	 }
	 
	 
	 @ResponseBody
	 @RequestMapping(value="admin/edit1",   params={"fileName"})
	 public String editContent1(
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
				//message.setContent(content.getHtmlContent());
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
		    model.addAttribute("content", message);
		    return response ;
		}
	
	 @ResponseBody
	 @RequestMapping(value="admin/edit",   params={"fileName"})
	 public String editContent(
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
				//message.setContent(content.getHtmlContent());
				message.setMeta(metaValue);
				String response ="<p>blank reply</p>";
				try {		
					PageService svc = new PageService();
					response = svc.readUpPage("posts", metaValue);
					//response = connector.getResponse(message);
					//TextMessage reply = sender.simpleSend(message.toString()); 
					//response=reply.getText();
					content.setHtmlContent(response);
				} catch (Exception e) {			
					e.printStackTrace();
				}
			
			response=response.equals("")?"<p>blank reply</p>":response;
		    model.clear();
		    model.addAttribute("content", message);
		    return response ;
		}
	
	 
	 
	 @ResponseBody
	 @RequestMapping(value="admin/editTemplate",   params={"fileName"})
	 public String editTemplateContent(
		        final PostContent content, final BindingResult bindingResult, final ModelMap model,
		        @RequestParam(value="fileName", required=false) String metaValue) {
		    if (bindingResult.hasErrors()) {
		        return "seedstartermng";
		    }
		    
		    System.out.println(content.getHtmlContent());
		        logger.info("Searching "+metaValue  );
		        QMessage message = new QMessage();
				message.setHandler("fetch");
				//message.setContent(content.getHtmlContent());
				message.setMeta(metaValue);
				String response ="<p>blank reply</p>";
				try {		
					PageService svc = new PageService();
					response = svc.readUpPage("templates", metaValue);
					//response = connector.getResponse(message);
					//TextMessage reply = sender.simpleSend(message.toString()); 
					//response=reply.getText();
					content.setHtmlContent(response);
				} catch (Exception e) {			
					e.printStackTrace();
				}
			
			response=response.equals("")?"<p>blank reply</p>":response;
		    model.clear();
		   // model.addAttribute("content", message);
		    return response ;
		}
	
	 
	@RequestMapping(value="admin/contentSubmit")
	public String processContent(
	        final PostContent content, final BindingResult bindingResult, final ModelMap model) {
	    if (bindingResult.hasErrors()) {
	        return "x_content";
	    }
	    
	    System.out.println(content.getHtmlContent());
	   // this.seedStarterService.add(seedStarter);
	    
	  
	        logger.info("Writing "+content.getMeta()  );
	      
	        
	        QMessage message = new QMessage();
			message.setHandler("write");
			message.setContent(content.getHtmlContent());
			message.setMeta(content.getMeta());
			String response ="";
			try {
				PageService svc = new PageService();
				svc.writePage("posts/"+content.getMeta(), content.getHtmlContent());
				response = connector.getResponse(message);
				//TextMessage reply = sender.simpleSend(message.toString()); 
				//response=reply.getText();
				
			} catch (Exception e) {			
				e.printStackTrace();
			}
		
	     
	   // model.clear();
	    return "home";
	}
	
	@RequestMapping(value="admin/submitContentTemplate")
	public String processContentTemplate(
	        final PostContent content, final BindingResult bindingResult, final ModelMap model) {
	    if (bindingResult.hasErrors()) {
	        return "x_content";
	    }
	    
	    System.out.println(content.getHtmlContent());
	   // this.seedStarterService.add(seedStarter);
	    
	  
	        logger.info("Writing "+content.getMeta()  );
	      
	        
	        QMessage message = new QMessage();
			message.setHandler("write");
			message.setContent(content.getHtmlContent());
			message.setMeta(content.getMeta());
			String response ="";
			try {
				PageService svc = new PageService();
				svc.writePage("templates/"+content.getMeta(), content.getHtmlContent());
				//response = connector.getResponse(message);
				//TextMessage reply = sender.simpleSend(message.toString()); 
				//response=reply.getText();
				
			} catch (Exception e) {			
				e.printStackTrace();
			}
		
	     
	   // model.clear();
	    return "home";
	}
	
}
