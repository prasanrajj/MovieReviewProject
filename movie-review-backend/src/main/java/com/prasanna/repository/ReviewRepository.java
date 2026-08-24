package com.prasanna.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasanna.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	List<Review> findByMovieId(String movie_id);
}
