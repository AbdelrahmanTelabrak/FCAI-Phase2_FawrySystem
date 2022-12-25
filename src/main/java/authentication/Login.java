package authentication;

import controllers.User;

public class Login extends Authentication{
	public Login(String email, String password) {
		super(email,password);
		
	}
	
	public User userLogin() {
		for(User user: database.getUsers()) {
			if(user.getEmail().equals(email))
				return user;
		}
		return null;
		
	}
	
	public boolean verify() {
		for(User user :database.getUsers()) {
			if(user.getEmail().equals(email)) {
				if(user.getPassword().equals(password)) {
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}

}
