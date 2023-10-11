package org.nypl.journalsystem;
import java.util.ArrayList;
import java.util.List;

public class Article {
	private String title; 
	private int ID; 
	private List<Author> authors;
	
	public Article(String title, List<Author> authors, int ID) {
		this.title = title;
		this.ID = ID;
		this.authors = new ArrayList<Author>(authors);
	}
	
	public String getTitle() {
		return title;
	}

	public List<Author> getAuthors() {
		return authors;
	}
	
	
	public int getID() {
		return ID;
	}
	
	public boolean hasValidAuthors() {
        for (Author author : authors) {
            if (author.isValidAuthor()) {
                return true;
            }
        }
        return false;
    }

}
