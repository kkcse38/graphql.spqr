package com.java.graphql.spqr;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.graphql.spqr.dao.AuthorRepository;
import com.java.graphql.spqr.dao.BookRepository;
import com.java.graphql.spqr.model.Author;
import com.java.graphql.spqr.model.Book;

@SpringBootApplication
public class Application {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	@PostConstruct
	public void addBookData() {
		
//		bookRepository.deleteAll();
//		authorRepository.deleteAll();
		
		Author a1 = new Author("HC Verma");
		Author a2 = new Author("KC Sinha");
		
		authorRepository.save(a1);
		authorRepository.save(a2);
		
		Book b1 = new Book("Math","Pearson",a1,200,250);
		Book b2 = new Book("Phy","TMH",a2,300,500);
		
		bookRepository.save(b1);
		bookRepository.save(b2);
	}

}
