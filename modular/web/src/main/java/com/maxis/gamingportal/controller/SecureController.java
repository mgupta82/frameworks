package com.maxis.gamingportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecureController {
	
	private static final Logger logger = LogManager.getLogger(SecureController.class.getName());
	
	@RequestMapping(value="/secure", method = RequestMethod.GET)
	public ModelAndView test(ModelMap model){
		logger.info("Opening secure page");
		return new ModelAndView("secure");
	}		
	
	@RequestMapping(value="/auto", method = RequestMethod.GET)
	public ModelAndView auto(ModelMap model){
		logger.info("Auto Login");
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		 grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken("admin", null,grantedAuths);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ModelAndView("secure");
	}	

}
