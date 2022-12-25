package service_providers;

import java.util.ArrayList;

public class Choose_service_provider {
	public Choose_service_provider() {
		Service_provider vodafone=new cocrete_service_provider ();
		Service_provider Orange =new cocrete_service_provider ();
		Service_provider Etisalat =new cocrete_service_provider ();
		vodafone=new Phone_number(vodafone);
		vodafone=new Amount(vodafone);
		Orange=new Phone_number(Orange);
		Orange = new Amount(Orange);
		ArrayList<String>v=vodafone.get_answer();
		System.out.println(v);
	
	}
	

}
