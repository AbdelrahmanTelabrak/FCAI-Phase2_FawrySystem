package service_providers;

import java.util.ArrayList;
import java.util.Scanner;

public class Amount extends Field_decorator{

	public Amount(Service_provider sp) {
		super(sp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> get_answer() {
		ArrayList<String>pn=sp.get_answer();
		if(pn==null) {
			
			pn=new ArrayList<String>();
		}
		System.out.println("Enter your Amount");
		Scanner input=new Scanner(System.in);
		String amount = input.nextLine();
		pn.add(amount);
		//new choose_payment_method(amount);
		return pn;
	}

}
