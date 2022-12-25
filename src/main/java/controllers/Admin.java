package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User implements Subject{

//	private String adminId;
	private ArrayList<Observer> observers;
	String State;
	String email;
	public Admin(String email, String type, String password, String username, String adminId) {
		
		super(email, type, password, username);
//		this.adminId = adminId;
		observers = new ArrayList<Observer>();
	}

//	public String getAdminId() {
//		return adminId;
//	}
	
	public void AcceptRefund(Observer o) {
		observers.add(o);
	}
    public void RejectRefund(Observer o) {
    	observers.remove(o);
    }
    public void notifyObservers() throws IOException  {
    	for (Observer observer : observers) {
            observer.update(this.email,State);
        }
    }
    public void statesChanged() throws IOException {
	    notifyObservers();
    }
	
    public void setSate(String email,String State) throws IOException {
	    this.State=State;
	    this.email=email;
	    statesChanged();
    }
	

	
//	@Override
//	public String toString() {
//		return "Admin [adminId=" + adminId + ", username=" + username + ", email=" + email + ", password=" + password
//				+ ", type=" + type + "]";
//	}
	
	
}
