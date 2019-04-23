package com.java.graphql.spqr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.graphql.spqr.dao.AuthorRepository;
import com.java.graphql.spqr.dao.BookRepository;
import com.java.graphql.spqr.model.Author;
import com.java.graphql.spqr.model.Book;

import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@GraphQLMutation(name="book")
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@GraphQLQuery(name="books")
	public List<Book> findAllBook(){
		return bookRepository.findAll();
	}
	
	@GraphQLQuery(name="book")
	public Book findBookById(String id) {
		Optional<Book> bookOpt =  bookRepository.findById(id);
		if(!bookOpt.isPresent())
			throw new RuntimeException("Book doesn't exist with given id "+id);
		return bookOpt.get();
	}
	
	@GraphQLQuery(name="book")
	public Book findBookByName(String name) {
		Book book =  bookRepository.findBookByName(name);
		if(book == null)
			throw new RuntimeException("Book doesn't exist with name "+name);
		return book;
	}
	
	@GraphQLQuery
	public Author getAuthor(@GraphQLContext Book book) {
		//System.out.println("/nGet Author /n"+book.getAuthor());
		return authorRepository.findById(book.getAuthor().getId()).get();
	}
	
}
