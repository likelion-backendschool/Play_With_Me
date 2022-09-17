package com.idea5.playwithme.together.service;


import com.idea5.playwithme.timeline.exception.DataNotFoundException;
import com.idea5.playwithme.timeline.service.TimelineService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.exception.MemberNotFoundException;
import com.idea5.playwithme.member.repository.MemberRepository;
import com.idea5.playwithme.review.repository.ReviewRepository;
import com.idea5.playwithme.review.service.ReviewService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TogetherService {
    @Autowired
    TogetherRepository togetherRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    TimelineService timelineService;


    public void save(Long articleId, Long memberId){

        /**
         * Todo
         * 예외처리
         */
        Article article = articleRepository.findById(articleId).orElse(null);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new MemberNotFoundException("Member is Not Found");
        });


        Together together = Together.builder()
                .article(article)
                .member(member)
                .build();

        togetherRepository.save(together);

        // Together 저장 -> Timeline 자동 생성되도록
        timelineService.create(together, member, article);
    }

    public Together findById(long id) {
        return togetherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("no %d question not found,".formatted(id)));
    }

    @Transactional
    public void saveTogetherAndReview(Long articleId, List<Long> ids) {
        for(int i = 0; i < ids.size(); i++){
            this.save(articleId, ids.get(i));

            Long reviewerId = ids.get(i);
            for(int j = 0; j < ids.size(); j++){
                Long revieweeId = ids.get(j);
                if(reviewerId != revieweeId){
                    System.out.println("revieweeId = " + revieweeId);
                    System.out.println("reviewerId = " + reviewerId);
                    reviewService.save(articleId, revieweeId, reviewerId);
                }

            }
        }
    }

//    public  findTogetherListByMemberId(Member member) {
//    }
}
