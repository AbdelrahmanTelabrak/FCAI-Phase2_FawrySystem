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

import controllers.Admin;
import controllers.RefundFile;
import model.Response;
import payment.DiscountList;
import payment.Transactions;

@RestController
@RequestMapping("/AdminController")
public class AdminController {
	Admin a;
	RefundFile rf=new RefundFile();
	ArrayList<String> refunds = new ArrayList<String>();
	Transactions transactions=Transactions.getInstance();
	DiscountList discountsList = DiscountList.getInstance();
	AdminController(Admin a){
		this.a=a;
		this.a.AcceptRefund(rf);
	}
	
	
	@PostMapping("/makediscount")
	public Response <HashMap<String, Double>> makediscount(@RequestParam("ServiceName")String ServiceName, @RequestParam("Discount")int discountpersentage)
	{
		double discount = discountpersentage/100.0;
		discountsList.addDiscount(ServiceName, discount);
		Response <HashMap<String, Double>> res = new Response<>();
		res.setStatus(true);
		res.setMessage("Add discount successfully");
		res.object = discountsList.getDiscountList();
		return res;
	}
	
	
	@GetMapping("/gettransactions")
	public Response <ArrayList<String>> gettransactions()
	{
		Response <ArrayList<String>> res = new Response<>();
		res.setMessage("All Transactions : ");
		res.setStatus(true);
		res.object = Transactions.getTransactions();
		return res;
	}
	@GetMapping("/admin/refund")
	public Response<ArrayList<String>> showRefundRequests(){
		Response<ArrayList<String>> res = new Response<>();
		refunds=new ArrayList<String>();
		 
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
	
	@PutMapping("/admin/refund/review")
	public Response<String> reviewRefund(@RequestParam("Request num") int index, @RequestParam("State") String state){
		Response<String> res = new Response<>();
		
		String arr1[]=refunds.get(index-1).split("\\s");
		try {
			a.setSate(refunds.get(index-1), state);
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
