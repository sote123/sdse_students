package org.nypl.journalsystem;

public class Publisher {
	private String name; 
	private String location; 
	
	public Publisher(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return name + ", " + location;
	}
}



