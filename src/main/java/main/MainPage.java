package main;

import java.io.IOException;
import java.util.Scanner;

import authentication.Login;
import authentication.Register;
import controllers.Admin;
import controllers.AdminPage;
import controllers.Client;
import controllers.User;
import controllers.chooseservices;

public class MainPage {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		User user;
		
		while(true) {
			System.out.println("FawrySystem");
			System.out.print("If you want to sign up please enter 1 or if u have an account already enter 0: ");
			Scanner input=new Scanner(System.in);
			String ok;
			ok=input.nextLine();
			if(ok.equals("0")) {
				System.out.println("Email:");
			    String email = scanner.nextLine();
			    System.out.println("Password:");
			    String password = scanner.nextLine();			
			    Login login = new Login(email, password);
		     if(login.verify()) {
		    	 user = login.userLogin();
		    	 if(user.getType().equals("client")) {
		    		 Client c = (Client) user;
			    	 System.out.println(c.toString());
			    	 System.out.println("Welcome back!");
			    	 new chooseservices((Client)user);
		    	 }
		    	 else {
		    		 Admin a = (Admin) user;
		    		 System.out.println(a.toString());
			    	 System.out.println("Welcome back!");
			    	 
			    	 new AdminPage(a);
		    	 }
		    	 
		    	 
		     }else
				System.out.println("Email or password is incorrect");
			}
			else if(ok.equals("1")){
				System.out.println("Email:");
			
			    String email = scanner.nextLine();
			
			    System.out.println("Password:");
			
			    String password = scanner.nextLine();
			
			    System.out.println("User name:");
			
			    String uname = scanner.nextLine();
			
			    Register r=new Register(email,password,uname);
			
			    r.register();
			}else {
				System.out.println("Please Run Again And enter 0 or 1");
			}
		}
	}
}
