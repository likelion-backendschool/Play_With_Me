package com.idea5.playwithme.review.repository;

import com.idea5.playwithme.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}