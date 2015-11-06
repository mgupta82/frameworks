package com.maxis.gamingportal.report;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecureController {
	
	private static final Logger logger = LogManager.getLogger(SecureController.class.getName());
	
	@RequestMapping(value="/secure", method = RequestMethod.GET)
	public ModelAndView test(ModelMap model){
		logger.info("Opening secure page");
		return new ModelAndView("secure");
	}		

}
