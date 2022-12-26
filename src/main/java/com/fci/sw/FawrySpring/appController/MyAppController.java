package com.fci.sw.FawrySpring.appController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import authentication.Login;
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
	 RefundFile rf=new RefundFile();
	 
	 DiscountList discountsList = DiscountList.getInstance();
	 ArrayList<String> transactions = new ArrayList<String>();
	 ArrayList<String> refunds=new ArrayList<String>();
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
	    		 c = (Client) user;
		    	 System.out.println(c.toString());
	    	 }
	    	 else {
	    		 a = (Admin) user;
	    		 a.AcceptRefund(rf);
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
	@GetMapping("/search")
	public  Response<String> search(@RequestParam("choose_Service")String service_name) {
		List<String> servicesList = new ArrayList<>();
		 Response<String> res = new Response<String>();
		servicesList.add("mobile recharge");
		servicesList.add("internet payment");
		servicesList.add("landline");
		servicesList.add("donations");
		
		System.out.print("Search on: ");
	    String search = service_name;
	    search = search.toLowerCase();
	    boolean foundResult = false;
	    for(String i: servicesList){
	    	if(i.contains(search)) {
	    		foundResult = true;
	    		res.setMessage("serach found sucssefully");
	    		res.object=i;
	    		res.setStatus(true);
	    	}
	    	
	    }
	   
	    if(!foundResult) { 
	    	res.setMessage("service not found");
	    	res.setStatus(false);
	    
	    }
	    
	   
		return res;
		}
	@PostMapping("/payment")
	public Response<Order> payment(@RequestParam("Service_name")String payment,@RequestParam("Payment_method")String name,@RequestParam("Cost")String cost) {
		ClientCreator cc=new ClientCreator();
		Services services = cc.fawryPayment(payment);
		Response<Order> res = new Response<>();
		Receipt reciept = null;
    	try {
			services.get_Providers();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int option2=0;
    	/*for(Service_provider i : services.getProviders()) {
    		option2++;
    		if(i.getName().equals(name)) {	
    	        break;
    	        }
    	}*/
    	//ArrayList<String> answers = services.getProviders().get(option2-1).get_answer();
    	Order order = new Order(c.getEmail(),payment.replaceAll("\\s", ""),cost);
    	transactions.add(order.getorder());
    	reciept = new Receipt(order);
    	new choose_payment_method(reciept,c,name);
    	res.setStatus(true);
    	res.setMessage("payment done sucssefully");
    	if(!reciept.getOrderDetails().getServiceePrice().equals("NotEnough")) {
    		c.addOrder(reciept.getOrderDetails());
    		res.object=reciept.getOrderDetails();
    	}
    	else {
    		res.setStatus(false);
    		res.setMessage("Not Enough");
		}
    	
    	return res;
    }	
	
	@GetMapping("/showRefund")
	public  Response<ArrayList<Order>> ShowOrders(){
		Response<ArrayList<Order>> res = new Response<>();
		res.object = c.getOrderlist();
		res.setStatus(true);
		res.setMessage("Show Order sucssefully");
		return res;
	}
	@PostMapping("/makeeRfund")
	public  Response<ArrayList<Order>> makerefund(@RequestParam("NumberofRefund")int NumberofRefund) throws IOException{
		Response<ArrayList<Order>> res = new Response<>();
		ArrayList<Order> ordersList =c.getOrderlist();
		if(ordersList.size()>0) {
			RefundFile r =new RefundFile();
			transactions.add(c.getEmail()+" Refund : "+ordersList.get(NumberofRefund-1).getEmail()+" "+ordersList.get(NumberofRefund-1).getServiceName()+" "+ordersList.get(NumberofRefund-1).getServiceePrice()+" Pending");
	    	r.changeInFile(ordersList.get(NumberofRefund-1));
	    	c.getOrderlist().remove(NumberofRefund-1);
	    	res.object = c.getOrderlist();
			res.setStatus(true);
			res.setMessage("make refund order done sucssefully");
		}else {
			res.setStatus(false);
			res.setMessage("Not Found Oeders");
		}
		return res;
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
		res.object = transactions;
		return res;
	}
	
	
	@PostMapping("/wallet")
	public Response rechargewallet(@RequestParam("amount") int amount)
	{
		c.setWalletBalance(c.getWalletBalance()+amount);
		Response res = new Response<>();
		res.setStatus(true);
		res.setMessage("Recharge Successfully, Your wallet balance : " + c.getWalletBalance());
		transactions.add(c.getEmail()+" Recharge wallet : " + amount);
		return res;
	}
	
	@GetMapping("/refund")
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
	
	@PostMapping("/refund/review")
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
