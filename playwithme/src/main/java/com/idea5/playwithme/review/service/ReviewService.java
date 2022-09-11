package com.idea5.playwithme.review.service;

import com.idea5.playwithme.review.domain.Review;
import com.idea5.playwithme.review.domain.ReviewDto;
import com.idea5.playwithme.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getReviewList(Long articleId, Long reviewerId) {
        // 아직 평가를 진행하지 않은 사람만 조회
        return reviewRepository.findByArticle_IdAndReviewerIdAndScoreLike(articleId, reviewerId, 0);
    }

    public void review(ReviewDto reviewDto) {
        List<Review> reviewList = reviewDto.getReviewList();

        for(Review r : reviewList) {
            // 평가 점수 반영
            Review review = reviewRepository.findById(r.getId()).orElse(null);
            review.setScore(r.getScore());
            reviewRepository.save(review);
        }
    }
}
