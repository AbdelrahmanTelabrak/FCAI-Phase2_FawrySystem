package com.fci.sw.FawrySpring.appController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import controllers.Client;
import controllers.RefundFile;
import model.Response;
import payment.DiscountList;
import payment.Order;
import payment.Receipt;
import payment.Transactions;
import payment.choose_payment_method;
import services.ClientCreator;
import services.Services;
@RestController
@RequestMapping("/ClientController")
public class ClientController {
	Client c;
	Transactions transactions=Transactions.getInstance();
	DiscountList discountsList = DiscountList.getInstance();
	ClientController(Client c){
		this.c=c;
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
    	Transactions.setTransactions(order.getorder());
    	c.addOrder(order);                         //<==
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
	
	@GetMapping("/showOrder")
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
			Transactions.setTransactions(c.getEmail()+" Refund : "+ordersList.get(NumberofRefund-1).getEmail()+" "+ordersList.get(NumberofRefund-1).getServiceName()+" "+ordersList.get(NumberofRefund-1).getServiceePrice()+" Pending");
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
	@PostMapping("/wallet")
	public Response rechargewallet(@RequestParam("amount") int amount)
	{
		c.setWalletBalance(c.getWalletBalance()+amount);
		Response res = new Response<>();
		res.setStatus(true);
		res.setMessage("Recharge Successfully, Your wallet balance : " + c.getWalletBalance());
		Transactions.setTransactions(c.getEmail()+" Recharge wallet : " + amount);
		return res;
	}
	@GetMapping("/showDiscountList")
	public Response<HashMap<String, Double>> showDiscounts(){
		Response<HashMap<String, Double>> res = new Response<>(); ;
		res.object=discountsList.getDiscountList();
		res.setStatus(true);
		res.setMessage("Show Discount List successfully");
		return res;
	}
	
}
