package services;

import controllers.Client;

public class ClientCreator {

	public  Services createServices(String type){
        if (type.equals("mobile recharge")) {
            return new MobileRecharge();
        } else if (type.equals("internet payment")) {
            return new InternetPayment();
        } else if (type.equals("landline")) {
            return new Landline();
        } else if (type.equals("donations")) {
         return new Donations();
        }
        return null;
    }
	
	
	
	public Services fawryPayment(String type) {
		Services services;
		services = createServices(type);
//		services.payment();
		return services;
	}
}
