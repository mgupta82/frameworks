package com.maxis.gamingportal.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView test(ModelMap model){
		logger.info("Opening login page");
		return new ModelAndView("login");
	}	
	
}
