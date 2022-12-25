package controllers;

import java.io.IOException;


public interface Observer {
	public void update(String email,String state) throws IOException;
}
