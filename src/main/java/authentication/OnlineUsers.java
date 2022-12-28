package authentication;

import java.util.HashMap;

import controllers.User;
import payment.DiscountList;

public class OnlineUsers {
	private static OnlineUsers instance = new OnlineUsers();
	
	private static HashMap<String, User> onlineUsers = new HashMap<>();
	
	private OnlineUsers() {}
	
	public static  OnlineUsers getInstance() {
		return instance;
	}

	public HashMap<String, User> getUsersList() {
		return onlineUsers;
	}
	
	public void addUser(String serviceName, User user) {
		onlineUsers.put(serviceName, user);
	}
}
