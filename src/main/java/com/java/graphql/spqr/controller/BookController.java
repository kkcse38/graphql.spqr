package com.java.graphql.spqr.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.graphql.spqr.model.Book;
import com.java.graphql.spqr.service.BookService;

import io.leangen.graphql.annotations.GraphQLEnvironment;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	Set<String> s;
	
	@GetMapping("/book")
	public List<Book> getBookLidt(){
		return bookService.findAllBook(s);
	}
}
