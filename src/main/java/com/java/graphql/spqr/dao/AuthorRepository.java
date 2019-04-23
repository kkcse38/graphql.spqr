package com.java.graphql.spqr.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.java.graphql.spqr.model.Author;

public interface AuthorRepository extends MongoRepository<Author, String>{
	
}
