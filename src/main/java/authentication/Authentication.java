package authentication;

import java.util.ArrayList;

public class Authentication {
	String email;
	String password;
	public Authentication(String e,String p) {
		
		email=e;
		password=p;
	}
	protected Database database = Database.getInstance();
	
//	public ArrayList<User> getUsers(){
//		ArrayList<User> users = new ArrayList<>();
//		String arr[] = database.getLine().split("\\\\");
//		for(String user: arr) {
//			String userData[] = user.split(",");
//			users.add(new Client(userData[2], Integer.valueOf(userData[3]), userData[0], userData[1], Double.valueOf(userData[4])));
//		}
//		return users;
//	}
}
