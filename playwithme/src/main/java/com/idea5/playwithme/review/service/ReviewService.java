package com.idea5.playwithme.review.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.repository.MemberRepository;
import com.idea5.playwithme.review.domain.Review;
import com.idea5.playwithme.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
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
                .build();

        reviewRepository.save(review);

    }
}