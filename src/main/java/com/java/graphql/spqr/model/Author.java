package com.java.graphql.spqr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("authorCollection")
public class Author {
	
	@Id
	private String id;
	
	private String name;
	
	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(String name) {
		super();
		this.name = name;
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
	
	
}
