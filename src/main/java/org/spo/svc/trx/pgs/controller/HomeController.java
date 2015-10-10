package org.spo.svc.trx.pgs.controller;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.ifs.template.EchoService;
import org.spo.svc.pages.gateway.model.QMessage;
import org.spo.svc.pages.gateway.svc.JmsQueueSender;
import org.spo.svc.pages.gateway.svc.SocketConnector;
import org.spo.svc.trx.pgs.cmd.M_Home_01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
			response = connector.getResponse(message);
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

    @RequestMapping(value = "/home/{contentId}", method = RequestMethod.GET)
    public String content(Locale locale, Model model,@PathVariable String contentId) {
        logger.info("Welcome home! the client locale is " + locale.toString());
        
        QMessage message = new QMessage();
		message.setHandler("pages");
		message.setContent("M_Content_1/f01/"+contentId);
		String response ="";
		try {		
			response = connector.getResponse(message);
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
        logger.info("Welcome home! the client locale is " + locale.toString());
      
        
        QMessage message = new QMessage();
		message.setHandler("templates");
		message.setContent("M_Home_1/f02/null");
		String response ="";
		try {		
			response = connector.getResponse(message);
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
    
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Locale locale, Model model) {
        logger.info("Welcome home! the client locale is " + locale.toString());

        return "about";
    }
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
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
