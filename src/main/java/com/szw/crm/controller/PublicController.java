package com.szw.crm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szw.crm.bean.PasswordTransResponse;
import com.szw.crm.bean.UserLoginRequest;
import com.szw.crm.entity.User;
import com.szw.crm.repository.UserRepository;
import com.szw.crm.util.PasswordUtil;

@Controller
public class PublicController {
	
	@Autowired
	private UserRepository userRepository ;
	
	@RequestMapping("/api/public/userLogin")
//	@ResponseBody
	public String userLogin(UserLoginRequest loginRequest, HttpServletRequest request) {
//		UserLoginResponse response = new UserLoginResponse() ;
		
		User user = this.userRepository.findByUsername(loginRequest.getUsername()) ;
		
		if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
			request.getSession().setAttribute("user", user);
			
//			response.setResult("SUCCESS");
//			
//			return response ;
			
			request.setAttribute("loginResult", "SUCCESS");
			
			return "redirect:/main" ;
		}
		
		request.setAttribute("loginResult", "FAIL");
		
		return "forward:/" ;
//		response.setResult("FAIL");
//		
//		return response ;
	}
	
	@RequestMapping("/api/public/userRegist")
//	@ResponseBody
	public String userRegist(UserLoginRequest registRequest, HttpServletRequest request) {
//		UserLoginResponse response = new UserLoginResponse() ;
		
		User user = this.userRepository.findByUsername(registRequest.getUsername()) ;
		
		if (user == null) {
			user = new User() ;
			user.setUsername(registRequest.getUsername());
			user.setPassword(registRequest.getPassword());
			user.setRole(User.ROLE.user);
			
			User returnUser = this.userRepository.save(user) ;
			
			request.getSession().setAttribute("user", returnUser);
//			
//			response.setResult("SUCCESS");
//			
//			return response ;
			
			request.setAttribute("registResult", "SUCCESS");
			
			return "redirect:/main" ;
		}
		
		request.setAttribute("registResult", "FAIL");
		
		return "forward:/" ;
		
//		response.setResult("FAIL");
//		
//		return response ;
	}
	
	@RequestMapping(value={ "/api/public/passwordTransformer/{password}" })
	public @ResponseBody PasswordTransResponse normalUserLogin(@PathVariable("password") String password) {
		
		PasswordTransResponse response = new PasswordTransResponse() ;
		
		try {
			password = PasswordUtil.aesEncrypt(password, PasswordUtil._key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setPassword(password);
		
		return response ;
	}
}
