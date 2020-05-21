package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/frontapi")
public class AdminController {

	@GetMapping("/login")
	public String loginPage() {
		return "/loginP";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/access-denied";
	}
	
	@GetMapping("/error")
	public String error() {
		return "/error";
	}
	
	@PostMapping("/goBack")
	public String goBack() {
		return "redirect:/games/";
	}
	
	@GetMapping("/")
	public String indexPage() {
		return "/index";
	}
}
