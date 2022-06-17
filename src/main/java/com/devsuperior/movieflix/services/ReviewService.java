package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.entities.repositories.MovieRepository;
import com.devsuperior.movieflix.entities.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public ReviewDTO insert(ReviewDTO dto) {
		User user = authService.authenticated();
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ReviewDTO(entity, user);
	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> findByMovie(Long id) {
		Optional<Movie> obj = movieRepository.findById(id);
		Movie movie = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		
		User user = authService.authenticated();
		List<Review> list = repository.find(movie);
		return list.stream().map(x -> new ReviewDTO(x, user)).collect(Collectors.toList());
	}
	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		entity.setText(dto.getText());
		entity.setMovie(new Movie(dto.getMovieId(), null,  null, null, null, null));			
	}
}