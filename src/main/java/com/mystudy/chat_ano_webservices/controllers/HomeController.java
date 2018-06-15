package com.mystudy.chat_ano_webservices.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {

	@RequestMapping(path="/")
	public String welcome()
	{
		return "redirect:/views/index.html";
	}
	
}
	