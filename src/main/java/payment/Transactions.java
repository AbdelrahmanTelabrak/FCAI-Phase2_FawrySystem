package payment;

import java.util.ArrayList;

import authentication.Database;

public class Transactions {

	private static Transactions instance = new Transactions();
	private static ArrayList<String> transactions = new ArrayList<String>();
	private Transactions() {
		
	}
	public static Transactions getInstance() {
		return instance;
	}
	public static ArrayList<String> getTransactions() {
		return transactions;
	}
	public static void setTransactions(String s) {
		transactions.add(s);
	}
	
}
