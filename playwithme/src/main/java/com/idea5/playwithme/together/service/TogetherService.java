package com.idea5.playwithme.together.service;



import com.idea5.playwithme.article.dto.ArticleDto;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.dto.EventDto;
import com.idea5.playwithme.event.service.EventService;
import com.idea5.playwithme.member.dto.MemberRecruitDto;


import com.idea5.playwithme.mypage.domain.Timeline;
import com.idea5.playwithme.mypage.exception.DataNotFoundException;
import com.idea5.playwithme.mypage.service.TimelineService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.domain.TogetherInfoDto;
import com.idea5.playwithme.together.exception.TogetherNotFoundException;
import com.idea5.playwithme.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.exception.MemberNotFoundException;
import com.idea5.playwithme.member.repository.MemberRepository;

import com.idea5.playwithme.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Transactional(readOnly = true)
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


    @Autowired
    EventService eventService;


    @Transactional
    public void save(Long articleId, Long memberId){

        /**
         * Todo
         * article 예외처리
         */

        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new MemberNotFoundException("Member is Not Found");
        });

        Article article = articleRepository.findById(articleId).orElse(null);
        Long eventId = article.getEventIdByBoard();
        Event event = eventService.getEvent(eventId);


        Together together = Together.builder()
                .article(article)
                .member(member)
                .event(event)
                .build();

        togetherRepository.save(together);
    }


    public Together findById(long id) {
        return togetherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("no %d timeline not found,".formatted(id)));
    }

    public List<Together> findByMemberId(long memberId){
        List<Together> togetherList = togetherRepository.findByMemberId(memberId);


        if(togetherList.size()==0){
            return null;
        }

        LocalDateTime now = LocalDateTime.now();

        List<Together> collect = togetherList.stream()
                .filter(t -> t.getEvent().getDate().isAfter(now))
                .sorted((e1,e2)-> e1.getEvent().getDate().compareTo(e2.getEvent().getDate()))
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional
    public void saveTogetherAndReview(Long articleId, List<Long> ids) {
        for(int i = 0; i < ids.size(); i++){
            this.save(articleId, ids.get(i));

            Long reviewerId = ids.get(i);
            // 동행 확정 폼 처리 시 -> Timeline 자동 생성되도록
            timelineService.save(articleId, ids.get(i));
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

    public List<TogetherInfoDto> findTogetherListByMemberId(Long member_id) {

        List<TogetherInfoDto> lists = new ArrayList<>();
        List<Together> togethers = togetherRepository.findByMemberId(member_id);
        for (Together together : togethers) {
            TogetherInfoDto togetherInfoDto = new TogetherInfoDto();

            Event event = together.getEvent();
            EventDto eventDto = EventDto.getDtoFromEntity(event);

            List<Object[]> members = memberRepository.findMembersByArticleIdAndMemberIdNot(getArticleId(together), member_id);

            for (Object[] objects : members) {
                String ninckname = objects[0].toString();
                Long id = (Long)objects[1];
                String username = objects[2].toString();

                Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member is Not Found..."));
                MemberRecruitDto memberDto = MemberRecruitDto.builder()
                        .id(member.getId())
                        .nickname(ninckname)
                        .username(username)
                        .build();

                togetherInfoDto.addMembers(memberDto);
            }
            togetherInfoDto.setEventDto(eventDto);
            togetherInfoDto.setId(together.getId());
            togetherInfoDto.setCreatedAt(together.getCreatedAt());
            togetherInfoDto.setArticleDto(ArticleDto.toDto(together.getArticle()));
            togetherInfoDto.setIsCancel(isDeadLine(together.getCreatedAt(), eventDto.getDate()));
            togetherInfoDto.setRemainDays(remainDay(together.getCreatedAt()));
            lists.add(togetherInfoDto);
        }

        for (TogetherInfoDto list : lists) {
            log.info("이벤트명 = " + list.getEventDto().getName());
            List<MemberRecruitDto> members = list.getMembers();
            for (MemberRecruitDto member : members) {
                log.info("이름 : " + member.getNickname());
            }
        }


        return lists;
    }

    public Long getArticleId(Together together){
        return together.getArticle().getId();
    }

    @Transactional
    public void doDelete(Long togetherId, Long memberId) {
        Together together = togetherRepository.findById(togetherId).orElseThrow(() ->
                new TogetherNotFoundException("Together is Not Found...."));

        Timeline timeline = timelineService.findByTogetherId(togetherId);
        timeline.setTogether(null);
        timelineService.deleteTimeline(timeline.getId());
        togetherRepository.deleteById(togetherId);
        reviewService.deleteReview(getArticleId(together), memberId);
        log.info("together is deleted ...");

    }


    /**
     * 마감 기한 지났는지
     */
    public Boolean isDeadLineFromTogetherDate(LocalDateTime createdTime){
        //        LocalDate now = LocalDateTime.of(2022,9,26,0,0).toLocalDate();
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDate togetherCreatedTime = createdTime.toLocalDate();
//        System.out.println("now = " + now);
//        System.out.println("togetherCreatedTime = " + togetherCreatedTime);

//        ChronoUnit.DAYS.between(a, b) between 파라미터의 순서는 반드시 이른 시간이 앞에, 늦은 시간이 뒤에 위치해야 한다.
        long between = ChronoUnit.DAYS.between(togetherCreatedTime, now)+1;
        log.info("between = " + between);


        return (between > 7) ? true : false;

    }

    /**
     * 이벤트 날짜 지났는지
     */
    public Boolean isDeadLineFromEventDate(LocalDateTime createdTime){
        LocalDateTime now = LocalDateTime.now();
        return createdTime.isBefore(now) ? true : false;
    }

    public Boolean isDeadLine(LocalDateTime togetherCreatedTime, LocalDateTime eventTime){
        /**
         * true : 마감기한이 지났음
         */
        Boolean state = isDeadLineFromTogetherDate(togetherCreatedTime);
        if(!state){
            state = isDeadLineFromEventDate(eventTime);
        }
        return state;
    }


    public Long remainDay(LocalDateTime createdTime){
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDate deadLineDay = createdTime.toLocalDate().plusDays(6);
        log.info("now : " + now);
        log.info("deadLineDay : " + deadLineDay);
        log.info("remainDay : " + (ChronoUnit.DAYS.between(now, deadLineDay)));

        return ChronoUnit.DAYS.between(now, deadLineDay)+1;
    }
}
