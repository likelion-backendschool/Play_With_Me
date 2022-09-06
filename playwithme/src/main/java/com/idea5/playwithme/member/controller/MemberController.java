package com.idea5.playwithme.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.KakaoUser;
import com.idea5.playwithme.member.exception.MemberNotFoundException;
import com.idea5.playwithme.member.service.KakaoService;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.together.domain.Together;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final KakaoService kakaoService;
    private final EventService eventService;

    private List<Together> togethers = new ArrayList<>();


    @GetMapping("/login/oauth/kakao/callback")
    public String redirectKakaoLogin(@RequestParam String code, HttpSession session) throws JsonProcessingException {
        // 사용자 코드로 엑세스 토큰 get
//        String accessToken = memberService.getAccessToken(code);
//
//        // 엑세스토큰으로 사용자 정보 get
//        KakaoUser kakaoUserInfo = memberService.getKakaoUserInfo(accessToken);
//
//        // PWM DB에서 회원 정보를 가려내는 유니크한 값
//        String username = kakaoUserInfo.getEmail() + "_" + kakaoUserInfo.getId();
//
//        // 회원 확인
//        Member member = null;
//        try {
//            member = memberService.findMember(username);
//        } catch (MemberNotFoundException e) {
//            // DB에 멤버 없으면 회원가입
//            member = memberService.join(kakaoUserInfo);
//        }
//
//        // 로그인
//        memberService.kakaoLogin(member);
//
//        // 로그아웃 처리 시, 사용할 토큰 값
//        session.setAttribute("accessToken", accessToken);
//        return "redirect:/";

        // 사용자 코드로 엑세스 토큰 get
        String accessToken = kakaoService.getAccessToken(code);

        // 엑세스토큰으로 사용자 정보 get
        KakaoUser kakaoUserInfo = kakaoService.getKakaoUserInfo(accessToken);

        // PWM DB에서 회원 정보를 가려내는 유니크한 값
        String username = kakaoUserInfo.getEmail() + "_" + kakaoUserInfo.getId();

        // 회원 확인
        Member member = null;
        try {
            member = memberService.findMember(username);
        } catch (MemberNotFoundException e) {
            // DB에 멤버 없으면 회원가입
            member = memberService.join(kakaoUserInfo);
        }

        // 로그인
        kakaoService.kakaoLogin(member);

        // 로그아웃 처리 시, 사용할 토큰 값
        session.setAttribute("accessToken", accessToken);
        return "redirect:/";
    }

    @GetMapping("/logout/oauth/kakao")
    public String redirectKakaoLogout(HttpSession session) throws JsonProcessingException {
        // 로그아웃 시 모든 세션 삭제 - 시큐리티 세션 삭제 목적
        session.invalidate();

        log.info("Logout done");
        return "redirect:/";
    }


    @GetMapping("/mypage/timeline") //TODO: 새로고침하면 리스트가 추가됨
    public String showTimeline(Model model, Principal principal, @RequestParam(defaultValue = "0") int page) {
        // 현재 로그인한 회원 리턴
        Member member = memberService.findMember(principal.getName());

        // 해당 회원의 Together 리스트
        togethers = member.getTogetherList();

        // 위 리스트에서 게시글 객체만 추출한 리스트
        List<Article> memberArticleList = togethers.stream().map(Together::getArticle).collect(Collectors.toList());

        // 위 리스트에서 게시판 객체만 추출한 리스트
        List<Board> memberArticleBoardList = memberArticleList.stream().map(Article::getBoard).collect(Collectors.toList());

        // 위 리스트에서 이벤트 id만 추출한 리스트
        List<Long> memberEventIdList = memberArticleBoardList.stream().map(Board::getId).collect(Collectors.toList());

        // 위 리스트 id에 해당하는 이벤트들 이벤트 리스트에 추가
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



