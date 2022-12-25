package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import service_providers.FieldFactory;
import service_providers.cocrete_service_provider;
import service_providers.Service_provider;

public class MobileRecharge extends Services{

	//List<String> providers = new ArrayList<>();
	
	public void payment(){
		System.out.println("Mobile Recharge succ");
	}
	
	

	public void get_Providers() throws FileNotFoundException {
		super.providers = new ArrayList<>();
		File file = new File("ServiceProviders.txt");
		Scanner scanner = new Scanner(file);
		FieldFactory f = new FieldFactory();
		while (scanner.hasNextLine()) {
		    String data = scanner.nextLine();
		    String provider[] = data.split("\\s");
		    Service_provider sp = new cocrete_service_provider();
		    if(provider[0].equals("mobile")) {
		   
		    	for(int i =2; i<provider.length; i++) {
		    		sp = f.createField(provider[i], sp);
		    	}
		    	sp.setName(provider[1]);
		    	super.providers.add(sp);
		    }
		    
		  }

	}
	
	
}
