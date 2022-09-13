package com.idea5.playwithme.review.service;

import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.review.domain.Review;
import com.idea5.playwithme.review.domain.ReviewDto;
import com.idea5.playwithme.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;

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
            float score = (float) ((r.getScore() - 3) / (reviewList.size() - 1) * 0.1); // 보정 점수
//            System.out.println("score = " + score);
            reviewee.modify(score);
        }
    }
}
