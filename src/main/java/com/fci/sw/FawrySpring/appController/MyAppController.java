package com.fci.sw.FawrySpring.appController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import authentication.Login;
import authentication.OnlineUsers;
import authentication.Register;
import controllers.Admin;
import controllers.Client;
import controllers.RefundFile;
import controllers.User;
import model.Response;
import payment.DiscountList;
import payment.Order;
import payment.Receipt;
import payment.choose_payment_method;
import service_providers.Service_provider;
import services.ClientCreator;
import services.Services;


@RestController
public class MyAppController {
	 Client c;
	 Admin a;
	 
	 OnlineUsers onlineUsers = OnlineUsers.getInstance();
	 
	@GetMapping("/login")
	public Response<User> login(@RequestParam("email") String email, @RequestParam("password") String password) {
		Login login = new Login(email, password);
		Response<User> res = new Response<>();
		if(login.verify()) {
	    	 User user = login.userLogin();
	    	 String uniqueID = UUID.randomUUID().toString();
	    	 res.object = user;
	    	 res.setStatus(true);
	    	 res.setMessage("Login successfully \n Please copy your id: "+uniqueID);
	    	 if(user.getType().equals("client")) {
	    		 c = (Client) user;
		    	 onlineUsers.addUser(uniqueID, c);
		    	 System.out.println(c.toString());
	    	 }
	    	 else {
	    		 a = (Admin) user;
		    	 onlineUsers.addUser(uniqueID, a);
	    		 System.out.println(a.toString());
	    	 }
	    	 
	     }else {
	    	 res.setStatus(false);
	    	 res.setMessage("Email or password are incorrect");
	     }
		
		return res;
	}
	@PostMapping("/register")
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
