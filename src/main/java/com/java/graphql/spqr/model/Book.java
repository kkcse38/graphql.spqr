package com.java.graphql.spqr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bookCollection")
public class Book {
	
	@Id
	private String id;
	
	private String name;
	
	private String bookPublisher;
	
	private Author author;
	
	private int pageCount;
	
	private float price;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}


	public Book(String name, String bookPublisher, Author author, int pageCount, float price) {
		
		this.name = name;
		this.bookPublisher = bookPublisher;
		this.author = author;
		this.pageCount = pageCount;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getBookPublisher() {
		return bookPublisher;
	}


	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}
	
	

}
