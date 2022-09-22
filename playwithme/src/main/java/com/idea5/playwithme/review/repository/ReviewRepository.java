package com.idea5.playwithme.review.repository;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByArticleIdAndReviewerId(Long ArticleId, Long reviewerId);
    List<Review> findByArticleIdAndRevieweeId(Long ArticleId, Long revieweeId);

    /**
     * findByFirstElementAndCriteriaOrSecondElementAndCriteria
     * (첫 번째 & 조건) OR ( 두 번째 & 조건) --> 조건 & ( 첫 번째 또는 두 번째)
     */
    List<Review> findByArticleIdAndReviewerIdOrArticleIdAndRevieweeId(Long ArticleId1, Long reviewerId, Long ArticleId2, Long revieweeId);

    // 리더가 동행을 취소할 경우
    void deleteByArticleId(Long articleId);
    List<Review> findByArticle_IdAndReviewerIdAndScoreLike(Long articleId, Long ReviewerId, Integer score);
    List<Review> findByReviewer_IdLikeAndScoreLike(Long memberId, Integer score);

}
