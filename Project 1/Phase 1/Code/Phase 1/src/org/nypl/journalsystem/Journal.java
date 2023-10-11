package org.nypl.journalsystem;

import java.util.ArrayList;
import java.util.List;

public class Journal {

	private String name;
	private Publisher publisher;
	private String issn;
	private List<Article> articles; 

	public Journal(String name, Publisher publisher, String issn, List<Article> articles) {
		this.name = name;
		this.publisher = publisher;
		this.issn = issn;
		this.articles = new ArrayList<Article>();
	}
	
	public boolean isFullIssue() {
		return articles.size() >= 3;
	}
	
	public void addArticle(Article article) {
		articles.add(article);
	}
	
	public String getName() {
		return name;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public String getIssn() {
		return issn;
	}

	public List<Article> getArticles() {
		return articles;
	}

}
