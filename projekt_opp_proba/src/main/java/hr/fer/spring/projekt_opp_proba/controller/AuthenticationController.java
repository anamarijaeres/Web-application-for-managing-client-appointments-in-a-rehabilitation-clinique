package hr.fer.spring.projekt_opp_proba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthenticationController {
	
	@GetMapping({"/", "welcome"})
	public ModelAndView openWolcomePage() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("welcome");
		return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView openLoginPage() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	 
	@GetMapping("/register")
	public ModelAndView openRegisterPage() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}
}
