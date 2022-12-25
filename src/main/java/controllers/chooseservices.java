package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import payment.BaseReciept;
import payment.DiscountList;
import payment.Order;
import payment.choose_payment_method;
import payment.Receipt;
import services.ClientCreator;
import services.Services;

import java.util.ArrayList;
public class chooseservices {

	ClientCreator cc;
	public chooseservices(Client client) throws IOException {
	
		cc = new ClientCreator();
		Services services; //= cc.fawryPayment("Internet");
		Order order;
		BaseReciept reciept;
		
		DiscountList list = DiscountList.getInstance();
		
		List<String> servicesList = new ArrayList<>();
		servicesList.add("mobile recharge");
		servicesList.add("internet payment");
		servicesList.add("landline");
		servicesList.add("donations");

		while(true) {
			Scanner input=new Scanner(System.in);
			System.out.println("Enter the number of the service you want:");
			System.out.println("1. Mobile Recharge");
			System.out.println("2. Internet Payment");
			System.out.println("3. Landline");
			System.out.println("4. Donations");
			System.out.println("5. Show available discounts");
			System.out.println("$. Search...");
			System.out.println("*. Make Refund..");
			System.out.println("#. Logout");
			



		    String option = input.nextLine();
		    
		    if(option.equals("1")) {
		    	services = cc.fawryPayment("mobile recharge");
		    	services.get_Providers();
		    	System.out.println("Enter the number of your provider:");
		    	services.showProviders();
		    	int option2 = input.nextInt();
		    	ArrayList<String> answers = services.getProviders().get(option2-1).get_answer();
		    	order = new Order(client.getEmail(),"mobileRecharge", answers.get(answers.size()-1));
		    	reciept = new Receipt(order);
		    	new choose_payment_method(reciept,client);
		    	if(!reciept.getOrderDetails().getServiceePrice().equals("NotEnough"))
		    		client.addOrder(reciept.getOrderDetails());
		    }
		    else if(option.equals("2")) {
		    	services = cc.fawryPayment("internet payment");

		    	services.get_Providers();
		    	System.out.println("Enter the number of your provider:");
		    	services.showProviders();
		    	int option2 = input.nextInt();
		    	ArrayList<String> answers = services.getProviders().get(option2-1).get_answer();
		    	order = new Order(client.getEmail(),"InternetPayment", answers.get(answers.size()-1));
		    	reciept = new Receipt(order);
		    	new choose_payment_method(reciept,client);
		    	client.addOrder(reciept.getOrderDetails());
		    }
		    else if(option.equals("3")) {
		    	services = cc.fawryPayment("landline");
		    	
		    	services.get_Providers();
		    	System.out.println("Enter the number of your provider:");
		    	services.showProviders();
		    	int option2 = input.nextInt();
		    	ArrayList<String> answers = services.getProviders().get(option2-1).get_answer();
		    	order = new Order(client.getEmail(),"landline", answers.get(answers.size()-1));
		    	reciept = new Receipt(order);
		    	new choose_payment_method(reciept,client);
		    	client.addOrder(reciept.getOrderDetails());
		    }
		    else if(option.equals("4")) {
		    	services = cc.fawryPayment("donations");
		    
		    	services.get_Providers();
		    	System.out.println("Enter the number of your provider:");
		    	services.showProviders();
		    	int option2 = input.nextInt();
		    	ArrayList<String> answers = services.getProviders().get(option2-1).get_answer();
		    	order = new Order(client.getEmail(),"donations", answers.get(answers.size()-1));
		    	reciept = new Receipt(order);
		    	new choose_payment_method(reciept,client);
		    	client.addOrder(reciept.getOrderDetails());
		    }
		    else if(option.equals("5")) {
		    	for (String name: list.getDiscountList().keySet()) {
		    	    String key = name.toString();
		    	    double value = Double.valueOf(list.getDiscountList().get(name))*100;
		    	    System.out.println(key + " " + value+"%");
		    	}
		    	if(list.getDiscountList().containsKey("mobile")) {
		    		
		    	}
		    }
		    else if(option.equals("$")) {
		    	System.out.print("Search on: ");
			    String search = input.nextLine();
			    search = search.toLowerCase();
			    
			    boolean foundResult = false;
			    for(String i: servicesList) {
			    	if(i.contains(search)) {
			    		System.out.println(i);
			    		foundResult = true;

			    		System.out.println("Do you want this service?(answer 0 or 1)");
			    		option = input.nextLine();
			    		if(option.equals("1")) {
			    			services = cc.fawryPayment(i);
			    			
			    			services.get_Providers();
					    	System.out.println("Enter the number of your provider:");
					    	services.showProviders();
					    	int option2 = input.nextInt();
					    	ArrayList<String> answers = services.getProviders().get(option2-1).get_answer();
					    	order = new Order(client.getEmail(),i, answers.get(answers.size()-1));
					    	reciept = new Receipt(order);
					    	new choose_payment_method(reciept,client);
					    	client.addOrder(order);
					    	
			    		}
			    		else
			    			continue;
			    	}
			    	
			    }
			    
			    if(!foundResult) 
			    	System.out.println("Search not found");
			    
		    }
		    else if(option.equals("*")) {
		    	int cnt=1;
		    	ArrayList<Order> ordersList =client.getOrderlist();
		    	if(ordersList.size()>0) {
		    		for(Order i: ordersList){
			    	    System.out.print(cnt+". ");
			    	    i.ShowOrder();
			    	    cnt++;
			    	}
			    	System.out.print("Enter Number of Refund: ");
			    	int x = input.nextInt();
			    	
			    	RefundFile r =new RefundFile();
			    	r.changeInFile(ordersList.get(x-1));
			    	client.getOrderlist().remove(x-1);
		    	}else {
		    		System.out.println("Not Found Oeders");
		    	}
		    	
		    	
		    }
		    else if(option.equals("#")) {
		    	break;
		    }
		}
	}

}
