package com.maxis.gamingportal.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex){
		ModelAndView model = new ModelAndView("error");
		ex.printStackTrace();
		model.addObject("ex", ex);
		return model;
	}	

}
