package com.fci.sw.FawrySpring.appController;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import authentication.Login;
import authentication.Register;
import controllers.Admin;
import controllers.Client;
import controllers.User;
import model.Response;

@RestController
public class MyAppController {
	
	@GetMapping("/login")
	public Response<User> login(@RequestParam("email") String email, @RequestParam("password") String password) {
		Login login = new Login(email, password);
		Response<User> res = new Response<>();
		if(login.verify()) {
	    	 User user = login.userLogin();
	    	 res.object = user;
	    	 res.setStatus(true);
	    	 res.setMessage("Login successfully");
	    	 if(user.getType().equals("client")) {
	    		 Client c = (Client) user;
		    	 System.out.println(c.toString());
	    	 }
	    	 else {
	    		 Admin a = (Admin) user;
	    		 System.out.println(a.toString());
		    	 	    	 
	    	 }
	    	 
	     }else {
	    	 res.setStatus(false);
	    	 res.setMessage("Email or password are incorrect");
	     }
		return res;
	}
	
	@GetMapping("/register")
	public Response register(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("username") String username) throws IOException {
	    Register r=new Register(email,password,username);
	
	    Response res = new Response<>();
	    if(r.register1()) {
	    	res.setStatus(true);
	    	res.setMessage("user registered successfully");
	    }
	    else {
	    	res.setStatus(false);
	    	res.setMessage("Username or email is used before");
	    }
		return res;
		
	}
	
}
