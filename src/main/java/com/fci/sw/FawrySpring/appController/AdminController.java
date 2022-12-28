package com.fci.sw.FawrySpring.appController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import authentication.OnlineUsers;
import controllers.Admin;
import controllers.RefundFile;
import model.Response;
import payment.DiscountList;
import payment.Transactions;

@RestController
@RequestMapping("/AdminController")
public class AdminController {
	RefundFile rf=new RefundFile();
	ArrayList<String> refunds = new ArrayList<String>();
	Transactions transactions=Transactions.getInstance();
	DiscountList discountsList = DiscountList.getInstance();
	OnlineUsers onlineUsers = OnlineUsers.getInstance();
	
	
	@PostMapping("/makediscount")
	public Response <HashMap<String, Double>> makediscount(@RequestParam("ServiceName")String ServiceName, 
			@RequestParam("Discount")int discountpersentage, @RequestParam("Id")String id)
	{
		Response <HashMap<String, Double>> res = new Response<>();
		if(!onlineUsers.getUsersList().containsKey(id) || !onlineUsers.getUsersList().get(id).getType().equals("admin")) {
			res.setStatus(false);
			res.setMessage("Not Authorized");
			return res;
		}
		Admin admin = (Admin) onlineUsers.getUsersList().get(id);
		double discount = discountpersentage/100.0;
		discountsList.addDiscount(ServiceName, discount);
		
		res.setStatus(true);
		res.setMessage("Add discount successfully");
		res.object = discountsList.getDiscountList();
		return res;
	}
	
	
	@GetMapping("/gettransactions")
	public Response <ArrayList<String>> gettransactions(@RequestParam("Id")String id)
	{
		Response <ArrayList<String>> res = new Response<>();
		if(!onlineUsers.getUsersList().containsKey(id) || !onlineUsers.getUsersList().get(id).getType().equals("admin")) {
			res.setStatus(false);
			res.setMessage("Not Authorized");
			return res;
		}
		Admin admin = (Admin) onlineUsers.getUsersList().get(id);
		admin.AcceptRefund(rf);
		res.setMessage("All Transactions : ");
		res.setStatus(true);
		res.object = Transactions.getTransactions();
		return res;
	}
	@GetMapping("/refund")
	public Response<ArrayList<String>> showRefundRequests(@RequestParam("Id")String id){
		Response<ArrayList<String>> res = new Response<>();
		refunds=new ArrayList<String>();
		if(!onlineUsers.getUsersList().containsKey(id) || !onlineUsers.getUsersList().get(id).getType().equals("admin")) {
			res.setStatus(false);
			res.setMessage("Not Authorized");
			return res;
		}
		Admin admin = (Admin) onlineUsers.getUsersList().get(id);
		admin.AcceptRefund(rf);
		try {
		      File myObj = new File("RefundRequest.txt");
		      Scanner myReader = new Scanner(myObj);
		     
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        refunds.add(data);
		    		    }
		      myReader.close();
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		res.object = refunds;
		res.setStatus(true);
		res.setMessage("Refund Requests: ");
		return res;
	}
	
	@PutMapping("/refund/review")
	public Response<String> reviewRefund(@RequestParam("Request num") int index, @RequestParam("State") String state,
			@RequestParam("Id")String id){
		Response<String> res = new Response<>();
		if(!onlineUsers.getUsersList().containsKey(id) || !onlineUsers.getUsersList().get(id).getType().equals("admin")) {
			res.setStatus(false);
			res.setMessage("Not Authorized");
			return res;
		}
		Admin admin = (Admin) onlineUsers.getUsersList().get(id);
		admin.AcceptRefund(rf);
		String arr1[]=refunds.get(index-1).split("\\s");
		try {
			admin.setSate(refunds.get(index-1), state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setStatus(true);
		res.setMessage("Request reviewed successfully.");
		res.object = arr1[0]+" "+arr1[1]+" "+arr1[2]+" "+state;
		return res;
	}
}
