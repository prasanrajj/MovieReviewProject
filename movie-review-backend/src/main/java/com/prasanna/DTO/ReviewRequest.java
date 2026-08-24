package com.prasanna.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public class ReviewRequest {

	String movieId;
	@Size(min = 3, max = 100, message = "Text must be between 3 and 100 characters")
	String text;

	@Min(value = 1)
	@Max(value = 5)
	Integer rating;

	public ReviewRequest() {
		super();
	}

	public ReviewRequest(String movie_id, String text, int rating) {
		super();
		this.movieId = movie_id;
		this.text = text;
		this.rating = rating;
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

	@Override
	public String toString() {
		return "Review [movie_id=" + movieId + ", text=" + text + ", rating=" + rating + "]";
	}

}
