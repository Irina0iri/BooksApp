package com.example.greatreadsapp.repos;

import com.example.greatreadsapp.model.Review;
import com.example.greatreadsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Optional<Review> findReviewByUser (User user);
}
