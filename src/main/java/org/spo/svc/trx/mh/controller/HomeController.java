package org.spo.svc.trx.mh.controller;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.cms.model.QMessage;
import org.spo.cms.svc.PageService;
import org.spo.cms.svc.SocketConnector;
import org.spo.ifs.template.EchoService;
import org.spo.ifs.template.web.Constants;
import org.spo.svc.trx.mc.cmd.PostContent;
import org.spo.svc.trx.mh.cmd.M_Home_01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory
            .getLogger(HomeController.class);

    @Autowired
    private EchoService echoService = null;
 
    
	private SocketConnector connector=new SocketConnector();
   // @Autowired
    //private JmsQueueSender sender;
    /**
     * Simple controller for "/" that returns a Thymeleaf view.
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! the client locale is " + locale.toString());
        QMessage message = new QMessage();
		message.setHandler("pages");
		message.setContent("M_Home_1/f01/null");
		String response ="";
		try {		
			PageService svc = new PageService(Constants.path_repo);
			response = svc.readUpPage("templates", "M_Home_01");
			//response = connector.getResponse(message);
			//TextMessage reply = sender.simpleSend(message.toString()); 
			//response=reply.getText();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		try{
			Gson gson = new Gson();
			Type typ = new TypeToken<M_Home_01>(){}.getType();//FIXME right now only string works
			M_Home_01 cmd= gson.fromJson(response,typ);		
			model.addAttribute("message",cmd);
			System.out.println(cmd.toString());
			
		}catch(Exception e){
			System.out.println("Error during messagePayload processing from  TestResourceServerException on" );
			e.printStackTrace();
		}
        Date date = new Date();
        DateFormat dateFormat =DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("echoService", echoService);
        model.addAttribute("someItems", new String[] { "one", "two", "three" });
        return "index";
    }

   
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Locale locale, Model model) {
    	PageService svc = new PageService(Constants.path_repo);
    	String	response = svc.readUpPage("templates", "M_Home_01");

    	try{
    		Gson gson = new Gson();
    		Type typ = new TypeToken<M_Home_01>(){}.getType();//FIXME right now only string works
    		M_Home_01 cmd= gson.fromJson(response,typ);		
    		model.addAttribute("message",cmd);
    		System.out.println(cmd.toString());

    	}catch(Exception e){
    		System.out.println("Error during messagePayload processing from  TestResourceServerException on" );
    		e.printStackTrace();
    	}
    	return "ma/index";
    }
    
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Locale locale, final ModelMap model) {

    	PostContent content = new PostContent();
		 PageService svc = new PageService(Constants.path_repo);
		 String response = svc.readUpPage("posts", "M_About");
		 response=response.equals("")?"<p>blank reply</p>":response;
		 model.clear();
		 content.setHtmlContent(response);
		 model.addAttribute("message", content);
        return "about";
    }
    @RequestMapping(value = "/contactold", method = RequestMethod.GET)
    public String contact(Locale locale, Model model) {
        logger.info("Welcome home! the client locale is " + locale.toString());

        return "contact";
    }
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String post(Locale locale, Model model) {
        logger.info("Welcome home! the client locale is " + locale.toString());

        return "post";
    }
  
}
