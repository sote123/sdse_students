package org.nypl.journalsystem;

public class Author {
	private String name; 
	private int ID; 
	
	public Author(String name, int ID){
		this.name = name; 
		this.ID = ID;
	}

	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
	
	// TO DO: Provide that at least one author is provided for each article 
	public boolean isValidAuthor() {
        return name != null && !name.isEmpty() && ID > 0;
    }
	
	@Override
	public String toString() {
		return name;
	}

}
