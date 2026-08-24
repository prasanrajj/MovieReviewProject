package com.prasanna.builder;

import java.util.ArrayList;
import java.util.List;

import com.prasanna.DTO.ReviewRequest;
import com.prasanna.DTO.ReviewResponse;
import com.prasanna.model.Review;

public class ReviewBuilder {
	public static String sentimentAnalyzer(String text) {
		String sentiment = "";
		List<String> sentimentGood = new ArrayList<>();
		sentimentGood.add("good");
		sentimentGood.add("great");
		sentimentGood.add("amazing");
		sentimentGood.add("excellent");
		sentimentGood.add("brilliant");
		sentimentGood.add("love");
		sentimentGood.add("fantastic");
		sentimentGood.add("super");
		sentimentGood.add("hit");
		sentimentGood.add("blockbuster");
		List<String> sentimentBad = new ArrayList<>();
		sentimentBad.add("bad");
		sentimentBad.add("terrible");
		sentimentBad.add("boring");
		sentimentBad.add("poor");
		sentimentBad.add("worst");
		sentimentBad.add("hate");
		sentimentBad.add("awful");
		sentimentBad.add("waste");

		text = text.toLowerCase();
		String[] split = text.split(" ");
		int goodWordCount = 0;
		int badWordCount = 0;
		for (String word : split) {
			if (sentimentGood.contains(word)) {
				goodWordCount++;
			}
			if (sentimentBad.contains(word)) {
				badWordCount++;
			}
		}
		if (goodWordCount > badWordCount) {
			sentiment = "Good";
		} else if (badWordCount > goodWordCount) {
			sentiment = "Bad";
		} else {
			sentiment = "Average";
		}
		return sentiment;
	}

	public static Review buildReviewFromReviewRequest(ReviewRequest reviewRequest) {
		return Review.builder().movieId(reviewRequest.getMovie_id()).rating(reviewRequest.getRating())
				.sentiment(sentimentAnalyzer(reviewRequest.getText())).text(reviewRequest.getText()).build();
	}

	public static ReviewResponse buildReviewResponseFromReview(Review review) {
		return ReviewResponse.builder().id(review.getId()).movieId(review.getMovie_id()).rating(review.getRating())
				.text(review.getText()).sentiment(review.getSentiment()).build();
	}
}
