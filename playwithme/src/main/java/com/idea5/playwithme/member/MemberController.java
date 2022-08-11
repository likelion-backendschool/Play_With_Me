package com.idea5.playwithme.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String loginForm() {
        return "member/loginForm";
    }

    @ResponseBody
    @GetMapping("/login/oauth/kakao/callback")
    public String kakaoCallback(@RequestParam String code) {
        // 요청 생성 라이브러리 
        // 카카오 인증서버로 POST 요청을 전달하기 위함
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "0c7de4f37c334c2d42105c51f0b9ab12");
        params.add("redirect_uri", "http://localhost:8080/member/login/oauth/kakao/callback");
        params.add("code", code);

        // HttpHeader, HttpBody 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // POST 방식으로 요청 전달
        // response 로 응답 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try{
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 토큰이용해서 사용자 정보 요청하기
        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        // 요청 및 응답 메시지
       ResponseEntity<String> kakaoProfileResponse = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        System.out.println(kakaoProfileResponse.getBody());

        // Gson, Json Simple, ObjectMapper 사용
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(kakaoProfileResponse.getBody());
        JsonElement kakaoAccount = element.getAsJsonObject().get("kakao_account");
//        String connectedAt = element.getAsJsonObject().get("connected_at").getAsString(); // 접속시간에 대한 정보
        String nickname = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
        String ageRange = kakaoAccount.getAsJsonObject().get("age_range").getAsString();
        String gender = kakaoAccount.getAsJsonObject().get("gender").getAsString();

        KakaoProfileDTO kakaoProfileDTO = new KakaoProfileDTO(email, ageRange, nickname, gender);
        return kakaoProfileDTO.toString();
    }
}
