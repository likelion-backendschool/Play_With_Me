package com.idea5.playwithme;

import org.springframework.stereotype.Component;

@Component("KakaoAddress")
public class KakaoAddress {
    private static String now;

    public String kakaoLogin(String now){
        this.now = now;
        return "https://kauth.kakao.com/oauth/authorize?client_id=0c7de4f37c334c2d42105c51f0b9ab12&redirect_uri=http://localhost:8080/member/login/oauth/kakao/callback&response_type=code";
    }
    public String kakaoLogout(String now){
        this.now = now;
        return "https://kauth.kakao.com/oauth/logout?client_id=0c7de4f37c334c2d42105c51f0b9ab12&logout_redirect_uri=http://localhost:8080/member/logout/oauth/kakao";
    }

    public static String getNow() {
        return now;
    }
}
