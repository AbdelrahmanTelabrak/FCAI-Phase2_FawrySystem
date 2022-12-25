package authentication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import controllers.User;

public class Register extends Authentication{
	private String username;
	public Register(String email, String password, String username) {
		super(email,password);
		this.username = username;
	}
	public void register() throws IOException {
		boolean ok=true;
		BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true));
		for(User c1: database.getUsers()){
			if(c1.getType().equals("client")) {
				
			  if(c1.getEmail().equals(email) || c1.getUserame().equals(username)) {
				
			     ok=false;	
			}
			}
		}
		try {
		if(ok) { 
		    writer.append('\n');
		    writer.append(email+" "+"client"+" "+password+" "+username+" "+0.0);
		    writer.close();
		  
			      System.out.println("Successfully wrote to the file.");
			}
		else {System.out.println("change email or username");}
		
		}
		catch (IOException e) {
            e.printStackTrace();

        }
    
		
	}
	
	public boolean register1() throws IOException {
		boolean ok=true;
		BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true));
		for(User c1: database.getUsers()){
			if(c1.getType().equals("client")) {
				
			  if(c1.getEmail().equals(email) || c1.getUserame().equals(username)) {
				
			     ok=false;	
			}
			}
		}
		try {
		if(ok) { 
		    writer.append('\n');
		    writer.append(email+" "+"client"+" "+password+" "+username+" "+0.0);
		    writer.close();
		  
			}
		
		}
		catch (IOException e) {
            e.printStackTrace();

        }
		return ok;
    
		
	}
	
}
