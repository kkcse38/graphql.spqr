package com.java.graphql.spqr.service;

import java.util.List;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.java.graphql.spqr.dao.AuthorRepository;
import com.java.graphql.spqr.dao.BookRepository;
import com.java.graphql.spqr.model.Author;
import com.java.graphql.spqr.model.Book;


import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;

import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

//@GraphQLApi is to impose this service for GraphQL
@Service
@GraphQLApi
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	//here book is root query name
	@GraphQLMutation(name="book")
	public Book saveBook(String name, String bookPublisher, @GraphQLArgument(name="auhor", defaultValue = "null") Author author, int pageCount, float price) {
		return bookRepository.save(new Book(name, bookPublisher, author, pageCount, price));
	}
	
	@GraphQLMutation(name="bookPrice")
	public Book updateBookPrice(String id, float price) {
		if(price <= 0)
			throw new RuntimeException("Book price can't be zero or less");
		Optional<Book> bookOpt =  bookRepository.findById(id);
		
		if(bookOpt.get() == null)
			throw new RuntimeException("Book doesn't exist with id "+id);
		
		Book book = bookOpt.get();
		book.setPrice(price);
		return bookRepository.save(book);
	}

	@GraphQLQuery(name="books")
	public List<Book> findAllBook(@GraphQLEnvironment Set<String> subSelectionFieldNames){
		
		Query query = new Query();
		
		//query.addCriteria(Criteria.where("id").is(id));
		
		for(String s : subSelectionFieldNames)
			query.fields().include(s);
		
		System.out.println("\n"+subSelectionFieldNames+"\n");
		
		return mongoTemplate.find(query, Book.class);
		
		//return bookRepository.findAll();
	}
	
	@GraphQLQuery(name="books")
	public List<Book> findBookById(String id, @GraphQLEnvironment Set<String> subSelectionFieldNames) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		
		//remove the author field if exist as it's not required
		subSelectionFieldNames.remove("author");
		
		//Set all the fields (need to fire) to the query
		for(String s : subSelectionFieldNames) {
			if(s.equals("author")) {
				continue;
			}else if(s.contains("/")) {
				query.fields().include(s.replace('/', '.'));
			}
			else
				query.fields().include(s);
		}
		
		//print the fields 
		System.out.println(subSelectionFieldNames);
		
		//Fire the query and get the required fields
		List<Book> bookList =  mongoTemplate.find(query, Book.class);
		
		//print the book details
		System.out.println("\n"+bookList.toString()+"\n");	
		
		return bookList;
		
	}
	
	@GraphQLQuery(name="book")
	public List<Book> findBookByName(String name, @GraphQLEnvironment Set<String> subSelectionFieldNames) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("name").in(name));
		
		for(String s : subSelectionFieldNames)
			query.fields().include(s);
		

		return mongoTemplate.find(query, Book.class);
		
//		Book book =  bookRepository.findBookByName(name);
//		if(book == null)
//			throw new RuntimeException("Book doesn't exist with name "+name);
//		return book;
	}
	
	@GraphQLQuery
	public Author getAuthor(@GraphQLContext Book book) {
		//System.out.println("/nGet Author /n"+book.getAuthor());
		return authorRepository.findById(book.getAuthor().getId()).get();
	}
	
	
}
