package payment;

import java.util.HashMap;

public class DiscountList {
	
	private static DiscountList instance = new DiscountList();
	
	private static HashMap<String, Double> discountList = new HashMap<>();

	private DiscountList() {}

	public static  DiscountList getInstance() {
		return instance;
	}

	public HashMap<String, Double> getDiscountList() {
		return discountList;
	}
	
	public void addDiscount(String serviceName, double discount) {
		discountList.put(serviceName, discount);
	}

}
