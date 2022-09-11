package com.idea5.playwithme.review.repository;

import com.idea5.playwithme.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByArticle_IdAndReviewerIdAndScoreLike(Long articleId, Long ReviewerId, Integer score);
}
