package com.devsuperior.movieflix.entities.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	@Query("SELECT obj FROM Review obj "
			+ "JOIN FETCH obj.user "
			+ "WHERE obj.movie = :movie ")
	List<Review> find(Movie  movie);
	
	@Query("SELECT obj FROM Review obj "
			+ "JOIN FETCH obj.user "
			+ "WHERE obj.movie = :movie  ")
	List<Review> findByReviews(Movie  movie);
}
