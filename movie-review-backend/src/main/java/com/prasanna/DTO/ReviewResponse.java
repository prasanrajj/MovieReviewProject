package com.prasanna.DTO;

import lombok.Builder;

@Builder
public class ReviewResponse {

	int id;
	String movieId;
	String text;
	Integer rating;
	String sentiment;

	public ReviewResponse() {
		super();
	}

	public ReviewResponse(int id, String movie_id, String text, int rating, String sentiment) {
		super();
		this.id = id;
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
		return "ReviewResponse [id=" + id + ", movie_id=" + movieId + ", text=" + text + ", rating=" + rating
				+ ", sentiment=" + sentiment + "]";
	}

}
