package service_providers;

import java.util.ArrayList;

public abstract class Field_decorator extends Service_provider{
	protected Service_provider sp;

	
	public Field_decorator(Service_provider sp) {
		
		this.sp=sp;
		
	}
	@Override
	public abstract ArrayList<String> get_answer();
		
	
}
