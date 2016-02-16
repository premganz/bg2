package org.spo.svc.trx.pgs.mx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.cms2.svc.SocketConnector;
import org.spo.svc.trx.pgs.mx.cmd.ContactForm;
import org.spo.svc.trx.pgs.mx.svc.MailMe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ContactPageController {

    private static final Logger logger = LoggerFactory
            .getLogger(ContactPageController.class);

   
 
    
	private SocketConnector connector=new SocketConnector();
	
	
	 @RequestMapping(value="/contact", method = RequestMethod.GET)
	 public String fetchPost( final ModelMap model  ) {
		ContactForm form = new ContactForm();

		 model.clear();
		 model.addAttribute("form", form);
		 return "contact" ;
	 }
	 
	 
	 @RequestMapping(value="/contactSubmit", method = RequestMethod.POST)
	 public String submitContact(    final ContactForm form, final BindingResult bindingResult, final ModelMap model
			) {
		 if (bindingResult.hasErrors()) {
			 return "seedstartermng";
		 }

		 System.out.println(form.getMessage());

		 MailMe svc = new MailMe();
		svc.mailContactForm(form);

		 
		 model.clear();
		 model.addAttribute("form", form);
		 return "contact" ;
	 }
	 
	 
	 
	
	
}
