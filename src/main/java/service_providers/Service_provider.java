package service_providers;

import java.util.ArrayList;

public abstract class Service_provider {
	String name;
	public abstract ArrayList<String> get_answer();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
