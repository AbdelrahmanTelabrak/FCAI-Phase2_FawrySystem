package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import payment.DiscountList;

public class AdminPage {

	public AdminPage(Admin admin) throws IOException {
		DiscountList discountsList = DiscountList.getInstance();
		RefundFile rf=new RefundFile();
		admin.AcceptRefund(rf);
		while(true) {
			
			System.out.println("Enter the number of Service:");
			System.out.println("1. Add Discount");
			System.out.println("2. Remove Discount");
			System.out.println("3. Review Refund Requests");
			System.out.println("$. Logout");

			Scanner input = new Scanner(System.in);
			String option = input.nextLine();
			double discount;
			if (option.equals("1")) {
				System.out.println("Enter the number of Service:");

				System.out.println("1. Mobile Recharge");
				System.out.println("2. Internet payment");
				System.out.println("3. Landline");
				System.out.println("4. All services");
				System.out.println("5. First Purchase");
				option = input.nextLine();
				System.out.println("Enter the percentage of the discount:");
				discount = input.nextDouble()/100;
				if (option.equals("1")) {
					discountsList.addDiscount("mobileRecharge", discount);
				}
				else if (option.equals("2")) {
					discountsList.addDiscount("InternetPayment", discount);
				}
				else if (option.equals("3")) {
					discountsList.addDiscount("landline", discount);
				}
				else if (option.equals("4")) {
					discountsList.addDiscount("all", discount);
				}
				else if (option.equals("5")) {
					discountsList.addDiscount("first time", discount);
				}

			}
			else if (option.equals("2")) {
				System.out.println("Enter the number of Service:");

				System.out.println("1. Mobile Recharge");
				System.out.println("2. Internet payment");
				System.out.println("3. Landline");
				System.out.println("4. All services");
				
				option = input.nextLine();
				if (option.equals("1")) {
					discountsList.getDiscountList().remove("mobile recharge");
				}
				else if (option.equals("2")) {
					discountsList.getDiscountList().remove("internet payment");
				}
				else if (option.equals("3")) {
					discountsList.getDiscountList().remove("landline");
				}
				else if (option.equals("4")) {
					discountsList.getDiscountList().remove("all");
				}
				
			}
			else if (option.equals("3")) {
				 ArrayList<String>arr=new ArrayList<String>();
				 
				try {
				      File myObj = new File("RefundRequest.txt");
				      Scanner myReader = new Scanner(myObj);
				     
				      while (myReader.hasNextLine()) {
				        String data = myReader.nextLine();
				        arr.add(data);
				    		    }
				      myReader.close();
				      
				    } catch (FileNotFoundException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				if(arr.size()>0) {
					int cnt=1;
			    	for(String i: arr){
			    	    System.out.println(cnt+". "+arr.get(cnt-1));
			    	
			    	    cnt++;
			    	    // output: 25461234
			    	}
			    	System.out.println(" \n Enter Number of Refund: ");
			    	int x = Integer.valueOf(input.nextLine());
			    	String arr1[]=arr.get(x-1).split("\\s");
			    	System.out.println("Enter the state (1 for accepted, 0 for rejected): ");
			    	String s;
			    	s=input.nextLine();
			    	if(s.equals("1"))
			    		admin.setSate(arr.get(x-1),"accepted");
			    	else if(s.equals("0"))
			    		admin.setSate(arr.get(x-1),"rejected");
			    		
				}
				else
					System.out.println("No refund requests available");
				
			}
			else if (option.equals("$")) {
				break;
			}

		}
	}
}
