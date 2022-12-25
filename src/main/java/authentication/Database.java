package authentication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Admin;
import controllers.Client;
import controllers.User;

public class Database {

	private static Database instance = new Database();
	private Scanner scanner;
	private static File file = new File("Users.txt");

	private Database() {
		
	}

	
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();
		try {
		      	scanner = new Scanner(file);
		      	while (scanner.hasNextLine()) {
		            String data = scanner.nextLine();
		            String userData[] = data.split("\\s");
//		            System.out.println(userData[1]);
		            if(userData[1].equals("client"))
		            	users.add(new Client(userData[0], userData[1], userData[2], userData[3], Double.valueOf(userData[4])));
		            else
		            	users.add(new Admin(userData[0], userData[1], userData[2], userData[3], userData[4]));
		            //System.out.println(data);
		          }
		      	scanner.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return users;
	}
	
	public static Database getInstance() {
		return instance;
	}
}
