package com.idea5.playwithme.review.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.exception.MemberNotFoundException;
import com.idea5.playwithme.member.repository.MemberRepository;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.review.domain.Review;
import com.idea5.playwithme.review.domain.ReviewDto;
import com.idea5.playwithme.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    /**
     * Todo
     * 예외처리
     */
    public void save(Long articleId, Long revieweeId, Long reviewerId){
        Member revieweeMember = memberRepository.findById(revieweeId).orElse(null);
        Member reviewerMember = memberRepository.findById(reviewerId).orElse(null);
        Article article = articleRepository.findById(articleId).orElse(null);

        Review review = Review.builder()
                .article(article)
                .reviewer(reviewerMember)
                .reviewee(revieweeMember)
                .score(0)
                .build();

        reviewRepository.save(review);

    }


    @Transactional
    public void deleteReview(Long articleId, Long memberId){
        List<Review> ReviewList = reviewRepository.findByArticleIdAndReviewerIdOrArticleIdAndRevieweeId(articleId, memberId, articleId, memberId);
        for (Review review : ReviewList)
            reviewRepository.deleteById(review.getId());
    }

    @Transactional
    public void deleteAllReview(Long articleId){
        reviewRepository.deleteByArticleId(articleId);
    }


//    public List<Event> getReviewList(Long reviewerId) {
//        List<Event> eventList = new ArrayList<>();
//
//        // 아직 평가를 하지 않은
//        List<Long> articleList = reviewRepository.findByReviewer_IdLikeAndScoreLike(reviewerId, 0);
//        for(Long articleId : articleList) {
//            Article article = articleRepository.findById(articleId).orElse(null);
//            eventList.add(article.getBoard().getEvent());
//        }
//        return eventList;
//    }

    public List<Review> getReviewList(Long articleId, Long reviewerId) {
        // 아직 평가를 진행하지 않은 사람만 조회
        return reviewRepository.findByArticle_IdAndReviewerIdAndScoreLike(articleId, reviewerId, 0);
    }

    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NullPointerException("%d review not found".formatted(reviewId)));
    }

    @Transactional
    public void review(ReviewDto reviewDto) {
        List<Review> reviewList = reviewDto.getReviewList();

        for(Review r : reviewList) {
            // review 테이블에 평가 점수 수정
            Review review = findById(r.getId());
            review.modify(r.getScore());
//            reviewRepository.save(review);

            // member 테이블에 매너온도 수정
            Member reviewee = memberService.findMember(r.getReviewee().getId());
            float score = (float) ((r.getScore() - 3) / reviewList.size() * 0.1); // 보정 점수
//            System.out.println("score = " + score);
            reviewee.modify(score);
        }
    }
}
