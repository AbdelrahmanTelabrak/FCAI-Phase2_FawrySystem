package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import payment.Order;


public class RefundFile implements Observer{
	
	String email;
	String State;
	public void update(String request,String state) throws IOException {
//		BufferedWriter writer = new BufferedWriter(new FileWriter("RefundRequest.txt", true));
		this.State=state;
		this.email=request;
		String requestLine[] = request.split("\\s");

		System.out.println(request+" "+state);
		 ArrayList<String>arr=new ArrayList<String>();
			try {
			      File myObj = new File("RefundRequest.txt");
			      Scanner myReader = new Scanner(myObj);
			     
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        arr.add(data);
			    		    }
			      myReader.close();
			      
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			double refund = 0;
			File inputFile = new File("RefundRequest.txt");
			File tempFile = new File("Temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String lineToRemove = null;
		for(String i:arr){
			
			if(i.trim().equals(request.trim())) {
				String arr1[]=(i).split("\\s");
				lineToRemove = i;
				BufferedWriter writer1 = new BufferedWriter(new FileWriter("Response.txt", true));
			    writer1.append(requestLine[0]+" "+arr1[1]+" " + arr1[2] + " "+state);
			    writer1.append('\n');
			    writer1.close();
			    refund = Double.valueOf(arr1[2]);
			    String currentLine;

				while((currentLine = reader.readLine()) != null) {
				    // trim newline when comparing with lineToRemove
				    String trimmedLine = currentLine.trim();
				    if(trimmedLine.equals(lineToRemove)) continue;
				    writer.write(currentLine + System.getProperty("line.separator"));
				}
				writer.close(); 
				reader.close(); 
				inputFile.delete();
				boolean successful = tempFile.renameTo(inputFile);
	    	   break;
			}
			
    	
    	  
    	    // output: 25461234
    	}
		
		if(state.equals("accepted"))
		{
			File inputFile2 = new File("Users.txt");
			File tempFile2 = new File("Temp.txt");
			BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(tempFile2));
			String lineToRemove2 = null;
			//Scanner input = new Scanner(inputFile2);
			String line;
			while((line = reader2.readLine()) != null)
			{
				//line = reader2.readLine();
				String a[] = line.split("\\s");
				if(a[0].equals(requestLine[0]))
				{
					lineToRemove2 = line;
					break;
				}
				
				else
				{
					continue;
				}
			}
			String currentLine;
			reader2.close();
			BufferedReader reader3 = new BufferedReader(new FileReader(inputFile2));
			while((currentLine = reader3.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove2)) continue;
			    writer2.write(currentLine + System.getProperty("line.separator"));
			}
			String clientData[] = lineToRemove2.split("\\s");
			double finalrefund = Double.valueOf(clientData[4])+refund;
			String newclient = clientData[0]+" "+clientData[1]+" "+clientData[2]+" "+clientData[3]+" "+finalrefund;
			writer2.write(newclient + System.getProperty("line.separator"));	
			writer2.close(); 
			reader3.close(); 
			Files.delete(Paths.get(inputFile2.getName()));
			//inputFile2.delete();
			boolean successful = tempFile2.renameTo(inputFile2);
		}
		
	// output: 25461234
	}
	
	public void changeInFile(Order order) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("RefundRequest.txt", true));
		writer.append(order.getEmail()+" "+order.getServiceName()+" "+order.getServiceePrice()+" Pending");
		writer.append('\n');
		writer.close();
		
		System.out.println("Your Request is Done..");
		
	}
	

}
