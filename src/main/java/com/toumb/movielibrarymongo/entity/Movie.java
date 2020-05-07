package com.toumb.movielibrarymongo.entity;

import org.springframework.data.annotation.Id;

public class Movie {
	
	@Id
	private int _id;
	
	private String title;
	private String category;
	private int year;
	private String plot;
	private String imageName;
	
	public Movie() {}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Movie [_id=" + _id + ", title=" + title + ", year=" + year + ", category=" + category + ", plot="
				+ plot + ", imageName=" + imageName + "]";
	}

}
