package com.java.graphql.spqr.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.java.graphql.spqr.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String>{
	public Book findBookByName(String name);
}
