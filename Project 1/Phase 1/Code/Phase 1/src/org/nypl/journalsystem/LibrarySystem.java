package org.nypl.journalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class LibrarySystem {
	private Map<String, Journal> journalsByISSN;
	private Map<Integer, Author> authorsByID;
	
	public LibrarySystem() {
		//DONE: Initialize system with default journals.
		journalsByISSN = new HashMap<>();
		authorsByID = new HashMap<>();
		
		Publisher springer = new Publisher("Springer", "Germany");
		Publisher elsevier = new Publisher("Elsevier", "Netherlands");
		Publisher natureResearch = new Publisher("Nature Research", "Great Britain");
		
		addJournal("Higher Education", springer, "0018-1560", null);
		addJournal("System", elsevier, "0346-2511", null);
		addJournal("Chem", elsevier, "2451-9294", null);
		addJournal("Nature", natureResearch, "1476-4687", null);
		addJournal("Society", springer, "0147-2011", null);
		
	}
	
	// Method to simplify the creation of journals + initializing the journalsByISSN lookup
	protected void addJournal(String name, Publisher publisher, String issn, List<Article> articles) {
		Journal journal = new Journal(name, publisher, issn, new ArrayList<>());
		journalsByISSN.put(issn, journal);
	}
	
	public void load() throws FileNotFoundException, IOException {
		loadAuthors();
		loadArticles();
	}
	
	protected void loadAuthors() throws FileNotFoundException, IOException {
		File file = new File("data/Authors.csv");

		//DONE: Load authors from file 
		Reader reader = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withIgnoreSurroundingSpaces().parse(reader);
		
		for (CSVRecord record : records) {
			int ID = Integer.parseInt(record.get(0));
			String name = record.get(1).trim();
			
			Author author = new Author(name, ID);
			authorsByID.put(ID, author);
		}

	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		File file = new File("data/Articles.csv");
		
		//DONE: Load articles from file and assign them to appropriate journal
		Reader reader = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withIgnoreSurroundingSpaces().parse(reader);
		
		for (CSVRecord record : records) {
			int articleID = Integer.parseInt(record.get(0));
			String title = record.get(1);
			
			List<Author> authors = new ArrayList<>();
			String[] authorIDs = record.get(2).substring(1, record.get(2).length() - 1).split(";");
			authorIDs = Arrays.stream(authorIDs)
	                 .map(String::trim) 
	                 .toArray(String[]::new);
			for (String authorID : authorIDs) {
			    Integer id = Integer.parseInt(authorID); 
			    Author author = authorsByID.get(id);
			    authors.add(author);
			}
			
			Article article = new Article(title, authors, articleID);
	
			String issn = record.get(3).trim();
			Journal journal = journalsByISSN.get(issn);
			journal.addArticle(article);
		}
		
	}
	
	
	public void listContents() {
		//TODO: Print all journals with their respective articles and authors to the console.
		System.out.println("Library contents:");
		
		for (Journal journal :journalsByISSN.values()) {
			System.out.println(formatJournal(journal));
			
			Collection<Article> articles = journal.getArticles();
			for (Article article : articles) {
				System.out.println(formatArticle(article));
			}
			
			System.out.println();
			}
		}
	
		
	
	protected String formatJournal(Journal journal) {
		String name = journal.getName();
		Publisher publisher = journal.getPublisher();
		String issn = journal.getIssn();
		boolean isFullIssue = journal.isFullIssue();
		
		String output = name;
		
		output += " (" + issn + ")";
		output += " by " + publisher;
		if (isFullIssue) {
			output += " is a full issue.";
		} else {
			output += " is not a full issue.";
		}
		
		return output;
	}
	
	
	protected String formatArticle(Article article) {
		String title = article.getTitle();
		String output = "\"" + title + "\" by ";
		
		Collection<Author> authors = article.getAuthors();
		boolean isFirst = true;
		
		for (Author author : authors) {
			if (!isFirst) {
				output += " and ";
			}
			output += author;
			isFirst = false;
			}
		return output;
		}


	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		librarySystem.listContents();
	}
}