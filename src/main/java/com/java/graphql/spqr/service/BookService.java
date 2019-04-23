package com.java.graphql.spqr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.graphql.spqr.dao.BookRepository;
import com.java.graphql.spqr.model.Book;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@GraphQLQuery(name="book")
	public List<Book> findAllBook(){
		return bookRepository.findAll();
	}
	
}
