package com.example.demo.controlfront;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Controller
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
		return "redirect:/frontapi/games/";
	}
	
	@GetMapping("/")
	public String indexPage() {
		return "/index";
	}
}
