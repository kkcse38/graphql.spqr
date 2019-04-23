package com.java.graphql.spqr;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.graphql.spqr.dao.BookRepository;
import com.java.graphql.spqr.model.Book;

@SpringBootApplication
public class Application {
	
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
	public void addBookData() {
		
		bookRepository.deleteAll();
		
		Book b1 = new Book("Math");
		Book b2 = new Book("Phy");
		
		bookRepository.save(b1);
		bookRepository.save(b2);
	}

}
