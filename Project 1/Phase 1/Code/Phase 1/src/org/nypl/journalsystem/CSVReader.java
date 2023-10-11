package org.nypl.journalsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class CSVReader {
	
	public static void readCSV(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
	        while ((line = br.readLine()) != null) {
	        	 System.out.println(line); 
	        }
		} catch (IOException e) {
	            e.printStackTrace();
		}	
	}
	
	public static final void main(String[] args) throws Exception {
		File file = new File("data/Articles.csv");
		readCSV(file);
	}
		
}
	


