package com.toumb.movielibrarymongo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.toumb.movielibrarymongo.entity.Movie;

@Controller
@RequestMapping("/movies")
public class MovieController {

	private MovieService movieService;
	
	public MovieController(MovieService theMovieService) {
		movieService = theMovieService;
	}
	
	@GetMapping("/list")
	public String listMovies(Model model) {
		// Get the movie list from the Service
		List<Movie> movies = movieService.findAll();
		
		// Add the movie list to the model
		model.addAttribute("movies", movies);
		
		return "movies/list-movies";
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model model) {
		// Create model attribute to bind form data
		Movie movie = new Movie();
		
		model.addAttribute("movie", movie);
		
		return "movies/movie-form";
	}
	
	@PostMapping("/save")
	public String saveMovie(@ModelAttribute("movie") Movie movie, BindingResult result, @RequestParam MultipartFile image) throws IOException {
		// Get image name and copy the file to a local folder
		// Get the image name
		String imageName = image.getOriginalFilename();
		movie.setImageName(imageName);
		// Create a copy of the image
		copy_file(image.getInputStream(), image.getOriginalFilename());
		
		// Save the movie using the service
		movieService.save(movie);
		
		return "redirect:/movies/list";
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("movieId") int id, Model model) {
		// Get movie from the service
		Movie movie = movieService.findById(id);
		// Set movie as a model attribute to pre-populate the form
		model.addAttribute("movie", movie);
		// Send to the form		
		return "movies/movie-form";
	}
	
	@GetMapping("/delete")
	public String deleteMovie(@RequestParam("movieId") int id) {
		Movie movie = movieService.findById(id);
		String path = SystemPath.path() + movie.getImageName();
		File file = new File(path);
		file.delete();
		// Delete the code record
		movieService.deleteById(id);
		
		return "redirect:/movies/list";
	}
	
	
	// Method to copy a file into a local folder, defined in SystemPath.java
	public void copy_file(InputStream file, String fileName) throws IOException {
		// Create the folder in which the files are stored
		String path = SystemPath.path();
		Path destinationPath = Paths.get(path);
		
		if(!Files.exists(destinationPath)) {
			try {
				Files.createDirectories(destinationPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Create a copy of the desired file in the directory
		File copiedFile = new File(path + fileName);
		
		try (				
			InputStream in = new BufferedInputStream(file);
			OutputStream out = new BufferedOutputStream(new FileOutputStream(copiedFile))) {
			
			byte[] buffer = new byte[1024];
			int lengthRead;
			while((lengthRead = in.read(buffer)) > 0) {
			    out.write(buffer, 0, lengthRead);
			    out.flush();
			}
		}
		
	}
	
}
