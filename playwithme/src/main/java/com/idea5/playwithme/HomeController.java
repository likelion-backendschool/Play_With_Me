package com.idea5.playwithme;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        String kakaoLoginLink = "https://kauth.kakao.com/oauth/authorize?client_id=0c7de4f37c334c2d42105c51f0b9ab12&redirect_uri=http://localhost:8080/member/login/oauth/kakao/callback&response_type=code";
        String kakaoLogoutLink = "https://kauth.kakao.com/oauth/logout?client_id=0c7de4f37c334c2d42105c51f0b9ab12&logout_redirect_uri=http://localhost:8080/member/logout/oauth/kakao";

        model.addAttribute("login", kakaoLoginLink);
        model.addAttribute("logout", kakaoLogoutLink);
        model.addAttribute("data", "홈 화면입니다.");

        return "home";
    }
}
