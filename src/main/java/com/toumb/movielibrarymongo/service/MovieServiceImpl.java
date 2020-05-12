package com.toumb.movielibrarymongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toumb.movielibrarymongo.dao.MovieRepository;
import com.toumb.movielibrarymongo.entity.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository theMovieRepository) {
		movieRepository = theMovieRepository;
	}
	
	@Override
	public List<Movie> findAll() {
		return movieRepository.findAllByOrderByTitleAsc();
	}

	@Override
	public Movie findById(int id) {
		Optional<Movie> result = movieRepository.findById(id);
		
		Movie movie = null;
		// Check if the movie exists
		if (result.isPresent()) {
			movie = result.get();
		} else {
			throw new RuntimeException("Unable to find the movie id - " + id);
		}
		
		return movie;
	}

	@Override
	public void save(Movie movie) {
		movieRepository.save(movie);
	}

	@Override
	public void deleteById(int id) {		
		movieRepository.deleteById(id);
	}

}
