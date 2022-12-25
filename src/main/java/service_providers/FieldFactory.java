package service_providers;

public class FieldFactory {

	public Field_decorator createField(String field, Service_provider sp) {
		if(field.equals("phone"))
			return new Phone_number(sp);
		else if(field.equals("home"))
			return new Home_number(sp);
		else if(field.equals("amount"))
			return new Amount(sp);
		else if(field.equals("reciept"))
			return new Receipt(sp);
		return null;
	}
	
}
