package com.idea5.playwithme.review.service;

import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.review.domain.Review;
import com.idea5.playwithme.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ArticleRepository articleRepository;

    public List<Review> getReviewList(Long articleId, Long reviewerId) {
        // 아직 평가를 진행하지 않은 사람만 조회
        return reviewRepository.findByArticle_IdAndReviewerIdAndScoreLike(articleId, reviewerId, 0);
    }
}
