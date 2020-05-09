package com.toumb.movielibrarymongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.toumb.movielibrarymongo.entity.Movie;

public interface MovieRepository extends MongoRepository<Movie, Integer> {
	
	// Add a method to sort records by last name
	public List<Movie> findAllByOrderByTitleAsc();

}