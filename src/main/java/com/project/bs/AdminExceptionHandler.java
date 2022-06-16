package com.project.bs;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AdminExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex, Model m) {
		m.addAttribute("exception", ex.getClass().getName());
		m.addAttribute("message", ex.getMessage());
		return "/opps";
	}

}