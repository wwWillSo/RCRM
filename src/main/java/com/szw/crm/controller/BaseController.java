package com.szw.crm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szw.crm.entity.User;

@Controller
public class BaseController {
    
	@RequestMapping(value={"/", "/login", "/index"})
	public String index() {
		return "/views/index/index" ;
	}
	
	@RequestMapping("/403")
	public String error() {
		return "/views/error" ;
	}
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) {
			return "redirect:/" ;
		} else {
			return "/views/main/main" ;
		}
	}
	
}
