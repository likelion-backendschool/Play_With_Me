package com.idea5.playwithme.together.service;



import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.mypage.exception.DataNotFoundException;
import com.idea5.playwithme.mypage.service.TimelineService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TogetherService {
    @Autowired
    TogetherRepository togetherRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    MemberRepository memberRepository;


    public void save(Long articleId, Long memberId){

        /**
         * Todo
         * 예외처리
         */
        Article article = articleRepository.findById(articleId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);

        Together together = Together.builder()
                .article(article)
                .member(member)
                .build();

        togetherRepository.save(together);
    }

    public Together findById(long id) {
        return togetherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("no %d timeline not found,".formatted(id)));
    }

    public List<Event> findByMemberId(long memberId){
        List<Together> togetherList = togetherRepository.findByMemberId(memberId);

        LocalDateTime now = LocalDateTime.now();

        List<Event> collect = togetherList.stream()
                .filter(t -> t.getArticle().getBoard().getEvent().getDate().isAfter(now))
                .map(t -> t.getArticle().getBoard().getEvent())
                .collect(Collectors.toList());
        return collect;
    }
}
