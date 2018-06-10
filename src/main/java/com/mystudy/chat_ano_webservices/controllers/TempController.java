package com.mystudy.chat_ano_webservices.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

	@RequestMapping(path="/",method=RequestMethod.GET)
	private String welcome1()
	{
		return "welcome";
	}
	
}
