package it.ManagerFlat.project.controller;


import it.ManagerFlat.project.service.ManagerFlat;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@Autowired
	ManagerFlat manager= new ManagerFlat();
	
//	@RequestMapping(value = "/index", method = RequestMethod.GET)
//	public String index( Model model) {
//
//		return "index";
//	}

	
}
