package com.java.graphql.spqr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.graphql.spqr.dao.AuthorRepository;
import com.java.graphql.spqr.model.Author;
import com.java.graphql.spqr.model.Book;

import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@GraphQLMutation(name="author")
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}
	
	@GraphQLQuery(name = "author")
	public Author findAuthorById(String id) {
		Optional<Author> opt =  authorRepository.findById(id);
		if(!opt.isPresent())
			throw new RuntimeException("Author doesn't exist for id "+id);
		return opt.get();
	}
	
	@GraphQLQuery(name = "authors")
	public List<Author> findAllAuthor() {
		return authorRepository.findAll();
	}
	
	@GraphQLQuery
	public Author getAuthor(@GraphQLContext Book book) {
		System.out.println("/nNook Author /n"+book.getAuthor());
		return author(book.getAuthor().getId());
	}
	
	@GraphQLQuery
	public Author author(String id) {
		return authorRepository.findById(id).get();
	}
	
}
