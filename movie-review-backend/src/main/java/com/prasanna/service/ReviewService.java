package com.prasanna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasanna.DTO.ReviewRequest;
import com.prasanna.DTO.ReviewResponse;
import com.prasanna.builder.ReviewBuilder;
import com.prasanna.exceptions.ReviewNotFoundException;
import com.prasanna.model.Review;
import com.prasanna.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;

	public ReviewResponse saveReview(ReviewRequest reviewRequest) {
		Review review = ReviewBuilder.buildReviewFromReviewRequest(reviewRequest);
		Review savedReview = reviewRepository.save(review);
		ReviewResponse reviewResponse = ReviewBuilder.buildReviewResponseFromReview(savedReview);
		return reviewResponse;
	}

	public List<ReviewResponse> getAllReviews() {
		List<Review> allReviews = reviewRepository.findAll();
		List<ReviewResponse> allReviewResponseList = allReviews.stream()
				.map(r -> ReviewBuilder.buildReviewResponseFromReview(r)).toList();
		return allReviewResponseList;
	}

	public ReviewResponse getAllReviewById(int id) {
		Review dbReview = reviewRepository.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
		ReviewResponse reviewResponse = ReviewBuilder.buildReviewResponseFromReview(dbReview);
		return reviewResponse;
	}

	public ReviewResponse updateReviewById(int id, ReviewRequest reviewRequest) {
		Review dbReview = reviewRepository.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
		Review savedReview;
		if (dbReview != null) {
			dbReview.setMovie_id(reviewRequest.getMovie_id());
			dbReview.setText(reviewRequest.getText());
			dbReview.setRating(reviewRequest.getRating());
			dbReview.setSentiment(ReviewBuilder.sentimentAnalyzer(reviewRequest.getText()));
			savedReview = reviewRepository.save(dbReview);
			return ReviewBuilder.buildReviewResponseFromReview(savedReview);
		}
		throw new RuntimeException("NOT FOUND WITH GIVEN ID " + id);
	}

	public String deleteReviewById(int id) {
		Review dbReview = reviewRepository.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
		reviewRepository.delete(dbReview);
		return "successfully deleted id " + id;
	}

	public List<ReviewResponse> getReviewsByMovieId(String movie_id) {
		List<Review> reviews = reviewRepository.findByMovieId(movie_id);
		return reviews.stream().map(ReviewBuilder::buildReviewResponseFromReview).toList();
	}

}
