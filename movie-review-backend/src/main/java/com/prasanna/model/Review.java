package com.prasanna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Entity
@Table(name = "reviews")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(nullable = false)
	String movieId;

	@NotBlank(message = "Review text is required")
	@Size(min = 3, max = 100, message = "Review must be between 3 and 100 characters")
	@Column(nullable = false, length = 100)
	private String text;
	
	@NotNull(message = "Rating is required")
	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating cannot exceed 5")
	@Column(nullable = false)
	private Integer rating;

	@Column(nullable = false)
	String sentiment;

	public Review() {
		super();
	}

	public Review(int id, String movie_id, String text, int rating, String sentiment) {
		super();
		this.id = id;
		this.movieId = movie_id;
		this.text = text;
		this.rating = rating;
		this.sentiment = sentiment;
	}

	public Review(String movie_id, String text, int rating, String sentiment) {
		super();
		this.movieId = movie_id;
		this.text = text;
		this.rating = rating;
		this.sentiment = sentiment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovie_id() {
		return movieId;
	}

	public void setMovie_id(String movie_id) {
		this.movieId = movie_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", movie_id=" + movieId + ", text=" + text + ", rating=" + rating + ", sentiment="
				+ sentiment + "]";
	}

}
