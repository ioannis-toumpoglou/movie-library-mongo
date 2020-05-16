package com.toumb.movielibrarymongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class LoginController {

	@GetMapping("/login")
	public String login() {
		
		return "security/login";
	}
	
	@GetMapping("/login-error")
	public String loginError() {
		
		return "security/login-error";
	}

	@GetMapping("/logout")
	public String logout() {
		
		return "security/login";
	}
}