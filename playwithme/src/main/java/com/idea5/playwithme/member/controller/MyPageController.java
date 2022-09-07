package com.idea5.playwithme.member.controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.together.domain.Together;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final MemberService memberService;
    private final EventService eventService;
    private List<Together> togethers = new ArrayList<>();


    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/timeline")
    public String showTimeline(Model model, Principal principal) {
        // principal null 체크
        if (principal == null) {
            log.info("You are Logged out. Login or Signin Please!");
            return "redirect:/";
        }

        // 현재 로그인한 회원 리턴
        Member member = memberService.findMember(principal.getName());

        // 해당 회원의 Together 리스트
        togethers = member.getTogetherList();

        // 위 리스트에서 게시글 객체만 추출
        List<Article> memberArticleList = togethers.stream().map(Together::getArticle).collect(Collectors.toList());

        // 위 리스트에서 게시판 객체만 추출
        List<Board> memberArticleBoardList = memberArticleList.stream().map(Article::getBoard).collect(Collectors.toList());

        // 위 리스트에서 이벤트 id만 추출
        List<Long> memberEventIdList = memberArticleBoardList.stream().map(Board::getId).collect(Collectors.toList());

        // memberEventIdList에 담긴 id들만 events 리스트에 추가
        List<Event> events = new ArrayList<>();
        for (Long t : memberEventIdList) {
            Event event = eventService.getEvent(t);
            events.add(event);
        }

        // 날짜(LocalDateTime) 내림차순 정렬 (최신 날짜가 상단에 오도록)
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if(o1.getDate().isAfter(o2.getDate())){
                    return -1;
                } else if(o1.getDate().isBefore(o2.getDate())){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        model.addAttribute("events", events);

        return "timeline";
    }
}