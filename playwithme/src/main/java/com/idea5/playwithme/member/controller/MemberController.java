package com.idea5.playwithme.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;


import com.idea5.playwithme.KakaoAddress;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.KakaoUser;
import com.idea5.playwithme.member.exception.MemberNotFoundException;
import com.idea5.playwithme.member.service.KakaoService;
import com.idea5.playwithme.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final KakaoService kakaoService;


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

}