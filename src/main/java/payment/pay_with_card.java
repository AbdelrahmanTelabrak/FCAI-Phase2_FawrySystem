package payment;

import controllers.Client;

public class pay_with_card extends payment_method {

	

	pay_with_card(BaseReciept reciept, Client creator) {
		super(reciept, creator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String performpay() {
		String serviceName = reciept.getOrderDetails().getServiceName();
		if(discountList.getDiscountList().containsKey("all")) {
		
			double discountValue = 0.0;
			if(discountList.getDiscountList().containsKey("first time") && creator.getOrderlist().size()==0) {
				discountValue += discountList.getDiscountList().get("first time");
			}
			discountValue += discountList.getDiscountList().get("all");
			reciept = new Discount(reciept, discountValue);
			System.out.println("Successfully pay with card "+reciept.getOrderDetails().getServiceePrice());
		}
		
		else if(discountList.getDiscountList().containsKey(serviceName)) {
		
			double discountValue = 0.0;
			if(discountList.getDiscountList().containsKey("first time") && creator.getOrderlist().size()==0) {
				discountValue += discountList.getDiscountList().get("first time");
			}
			discountValue += discountList.getDiscountList().get(serviceName);
			reciept = new Discount(reciept, discountValue);
			System.out.println("Successfully pay with card "+reciept.getOrderDetails().getServiceePrice());
		}
		else if(discountList.getDiscountList().containsKey("first time") && creator.getOrderlist().size()==0) {
			
			double discountValue = discountList.getDiscountList().get("first time");
			reciept = new Discount(reciept, discountValue);
			System.out.println("Successfully pay with card "+reciept.getOrderDetails().getServiceePrice());
		}
		else
			System.out.println("Successfully pay with card "+reciept.getOrderDetails().getServiceePrice());
		return reciept.getOrderDetails().getServiceePrice();
				
	}

}
