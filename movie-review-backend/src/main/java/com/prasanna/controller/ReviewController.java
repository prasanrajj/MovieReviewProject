package com.prasanna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.DTO.ReviewRequest;
import com.prasanna.DTO.ReviewResponse;
import com.prasanna.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/save")
	public ReviewResponse saveReview(@Valid @RequestBody ReviewRequest reviewRequest) {
		return reviewService.saveReview(reviewRequest);
	}
	
	@GetMapping("/get/reviews/all")
	public List<ReviewResponse> getAllReviews(){
		return reviewService.getAllReviews();
	}
	
	@GetMapping("/get/review/{id}")
	public ReviewResponse getReviewById(@PathVariable(name = "id") int id){
		return reviewService.getAllReviewById(id);
	}
	
	@GetMapping("/movie/{movie_id}")
	public List<ReviewResponse> getReviewsByMovieId(
	        @PathVariable String movie_id) {
	    return reviewService.getReviewsByMovieId(movie_id);
	}
	
	@PutMapping("/update/review/{id}")
	public ReviewResponse updateReviewById(@PathVariable(name = "id") int id, @Valid @RequestBody ReviewRequest reviewRequest) {
		return reviewService.updateReviewById(id, reviewRequest);
	}
	
	@DeleteMapping("/delete/review/{id}")
	public String deleteReviewById(@PathVariable(name = "id") int id) {
		return reviewService.deleteReviewById(id);
	}
	
	
}
