package com.java.graphql.spqr.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.java.graphql.spqr.model.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String>{
	
}
