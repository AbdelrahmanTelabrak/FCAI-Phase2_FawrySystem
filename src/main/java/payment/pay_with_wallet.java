package payment;

import java.io.File;
import java.io.IOException;

import controllers.Client;

public class pay_with_wallet extends payment_method {
	
	File textFile = new File("Users.txt");
	public pay_with_wallet(BaseReciept reciept ,Client creator) {
		super(reciept, creator);
		this.creator=creator;
	}

	@Override
	public String performpay() {
		double x =creator.getWalletBalance();
//		this.creator.walletBalance -= Double.valueOf(reciept.getOrderDetails().getServiceePrice());
		String y = null;
		String serviceName = reciept.getOrderDetails().getServiceName();
		if(discountList.getDiscountList().containsKey("all")) {
		
			double discountValue = 0.0;
			if(discountList.getDiscountList().containsKey("first time") && creator.getOrderlist().size()==0) {
				discountValue += discountList.getDiscountList().get("first time");
			}
			
			discountValue += discountList.getDiscountList().get("all");
			
			reciept = new Discount(reciept, discountValue);
			if(this.creator.getWalletBalance()>=Double.valueOf(reciept.getOrderDetails().getServiceePrice())) {
				this.creator.setWalletBalance(this.creator.getWalletBalance() - Double.valueOf(reciept.getOrderDetails().getServiceePrice()));
				y =  reciept.getOrderDetails().getServiceePrice();
				System.out.println("Successfully paid with wallet balance "+reciept.getOrderDetails().getServiceePrice()+" EGP");
				
			}else {
				System.out.println("Not Money Enough");
				y = "NotEnough";
			}
			
			
			
		}
		
		else if(discountList.getDiscountList().containsKey(serviceName)) {
		
			double discountValue = 0.0;
			if(discountList.getDiscountList().containsKey("first time") && creator.getOrderlist().size()==0) {
				discountValue += discountList.getDiscountList().get("first time");
			}
			discountValue += discountList.getDiscountList().get(serviceName);
			reciept = new Discount(reciept, discountValue);
			if(this.creator.getWalletBalance()>=Double.valueOf(reciept.getOrderDetails().getServiceePrice())) {
				this.creator.setWalletBalance(this.creator.getWalletBalance() - Double.valueOf(reciept.getOrderDetails().getServiceePrice()));
				y =  reciept.getOrderDetails().getServiceePrice();
				System.out.println("Successfully paid with wallet balance "+reciept.getOrderDetails().getServiceePrice()+" EGP");
				
			}else {
				System.out.println("Not Money Enough");
				y = "NotEnough";
			}
//			System.out.println("Successfully pay with card "+reciept.getOrderDetails().getServiceePrice());
		}
		else if(discountList.getDiscountList().containsKey("first time") && creator.getOrderlist().size()==0) {
			
			double discountValue = discountList.getDiscountList().get("first time");
			reciept = new Discount(reciept, discountValue);
			if(this.creator.getWalletBalance()>=Double.valueOf(reciept.getOrderDetails().getServiceePrice())) {
				this.creator.setWalletBalance(this.creator.getWalletBalance() - Double.valueOf(reciept.getOrderDetails().getServiceePrice()));
				y =  reciept.getOrderDetails().getServiceePrice();
				System.out.println("Successfully paid with wallet balance "+reciept.getOrderDetails().getServiceePrice()+" EGP");
				
			}else {
				System.out.println("Not Money Enough");
				y = "NotEnough";
			}
//			System.out.println("Successfully pay with card "+reciept.getOrderDetails().getServiceePrice());
		}
		else {
			if(this.creator.getWalletBalance()>=Double.valueOf(reciept.getOrderDetails().getServiceePrice())) {
				this.creator.setWalletBalance(this.creator.getWalletBalance() - Double.valueOf(reciept.getOrderDetails().getServiceePrice()));
				y =  reciept.getOrderDetails().getServiceePrice();
				System.out.println("Successfully paid with wallet balance "+reciept.getOrderDetails().getServiceePrice()+" EGP");
				
			}else {
				System.out.println("Not Money Enough");
				y = "NotEnough";
			}
		}
			
			
//			System.out.println("Successfully pay with card "+reciept.getOrderDetails().getServiceePrice());
//		try {
//            String data = FileUtils.readFileToString(textFile);
//            data = data.replace(x, y);
//            FileUtils.writeStringToFile(textFile, data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
		return y;
		
		
	}

}

//
//import java.io.File;
//import java.io.IOException;
// 
//import org.apache.commons.io.FileUtils;
// 
//public class Example {
//    /**
//     * An example program to replace a string in text file
//     */
//    public static void main(String[] args) {
//        
//        
//    }
// 
//}