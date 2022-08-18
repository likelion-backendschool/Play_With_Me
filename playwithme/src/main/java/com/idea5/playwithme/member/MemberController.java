package com.idea5.playwithme.member;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.idea5.playwithme.member.dto.KakaoUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class  MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "member/loginForm";
    }


    @GetMapping("/login/oauth/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        memberService.kakaoLogin(code, response); // 카카오 사용자 정보까지 가져옴  // 로그인까지 해야함
        return "redirect:/";
    }
}
