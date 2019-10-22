package com.example.demo.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;


@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "views/registerForm";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid User user, BindingResult bindingResults, Model model){
		if(bindingResults.hasErrors()) {
			return "views/registerForm";
		}
		if (userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist", true);
			return "views/registerForm";
		}
		userService.createUser(user);
		
		return "views/success";
	}

}
