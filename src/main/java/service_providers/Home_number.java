package service_providers;

import java.util.ArrayList;
import java.util.Scanner;

public class Home_number extends Field_decorator {

	public Home_number(Service_provider sp) {
		super(sp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> get_answer() {
		ArrayList<String>pn=sp.get_answer();
		if(pn==null) {
			
			pn=new ArrayList<String>();
		}
		System.out.println("Enter your Home number");
		Scanner input=new Scanner(System.in);
		pn.add(input.nextLine());
		return pn;
	}
}
