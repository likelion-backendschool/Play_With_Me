package com.idea5.playwithme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("KakaoAddress")
public class KakaoAddress {
    private static String now;

    private String host = "https://kauth.kakao.com";
    private String loginRequestUri = "/oauth/authorize";
    private String logoutRequestUri = "/oauth/logout";

    @Value("${oauth2.kakao.rest-api-key}")
    private String restApiKey;

    @Value("${oauth2.kakao.login.redirect-uri}")
    private String loginRedirectUri;

    @Value("${oauth2.kakao.logout.redirect-uri}")
    private String logoutRedirectUri;

    public String kakaoLogin(String now){
        this.now = now;
        return "%s%s?client_id=%s&redirect_uri=%s&response_type=code".formatted(host, loginRequestUri, restApiKey, loginRedirectUri);
    }
    public String kakaoLogout(String now){
        this.now = now;
        return "%s%s?client_id=%s&logout_redirect_uri=%s".formatted(host, logoutRequestUri, restApiKey, logoutRedirectUri);
    }

    public static String getNow() {
        return now;
    }
}
