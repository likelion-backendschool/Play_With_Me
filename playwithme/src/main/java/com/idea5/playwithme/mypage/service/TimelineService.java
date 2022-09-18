package com.idea5.playwithme.mypage.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.repository.MemberRepository;
import com.idea5.playwithme.mypage.domain.Timeline;
import com.idea5.playwithme.mypage.dto.TimelineRequestDto;
import com.idea5.playwithme.mypage.exception.DataNotFoundException;
import com.idea5.playwithme.mypage.repository.TimelineRepository;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TimelineService {
    private final TimelineRepository timelineRepository;
    private final TogetherRepository togetherRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;



    // 동행 확정 폼 처리 시 -> Timeline 자동 생성되도록
    @Transactional
    public void save(Long articleId, Long memberId) {
        Article article = articleRepository.findById(articleId).orElse(null);

        Member member = memberRepository.findById(memberId).orElse(null);

        Event event = article.getBoard().getEvent();

        Together together = togetherRepository.findByArticle_IdAndMember_Id(articleId, memberId);

        Timeline timeline = Timeline.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .event(event)
                .member(member)
                .together(together)
                .build();

        timelineRepository.save(timeline);
    }

    // CREATE
    @Transactional
    public void saveMemo(Long id, Member member, Together together, TimelineRequestDto timelineRequestDto, Event event) {
        Timeline timeline = findById(id);
        timeline.update(timelineRequestDto.getMemo());
        timelineRepository.save(timeline);
    }

    public Timeline findById(Long id){
        return timelineRepository.findById(id).orElseThrow(()-> new DataNotFoundException("해당 타임라인이 존재하지 않습니다"));
    }

    // UPDATE
    @Transactional
    public void updateMemo(Long id, TimelineRequestDto timelineRequestDto) {
        Timeline timeline = findById(id);
        timeline.update(timelineRequestDto.getMemo());
        timelineRepository.save(timeline);
    }

    // DELETE
    // timeline (x) timeline.memo (o)
    @Transactional
    public void deleteMemo(Long id) {
        Timeline timeline = findById(id);
        timeline.update();
        timelineRepository.save(timeline);
    }
}
