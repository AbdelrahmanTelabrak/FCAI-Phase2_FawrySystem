package controllers;

public abstract class User {

	protected String username;
	protected String email;
	protected String password;
	protected String type;
	
	public User(String email, String type, String password, String username) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public String getUserame() {
		return username;
	}


	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public String getType() {
		return type;
	}

	//public abstract Services fawryPayment(String type) ;
	
	
	@Override
	public String toString() {
		return "User [name=" + username + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
