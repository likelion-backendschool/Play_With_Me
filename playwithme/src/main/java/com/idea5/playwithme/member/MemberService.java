package com.idea5.playwithme.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.domain.RoleType;
import com.idea5.playwithme.member.dto.KakaoUser;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
//    private final AuthenticationManager authenticationManager;

    @Transactional
    public void kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        // 사용자 요청 코드를 이용해서 엑세스 토큰 요청
        String accessToken = getAccessToken(code);

        // 엑세스토큰을 이용해 사용자 정보 받아오기
        KakaoUser kakaoUserInfo = getKakaoUserInfo(accessToken);

//        // 사용자가 이미 가입된 회원인지 확인
//        Member originUser = findMember(kakaoUserInfo.getUsername());
//        if (originUser.getUsername() == null) {
//            join(kakaoUserInfo);
//        }

//        // 자동 로그인 진행
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(kakaoUserInfo.getUsername(), cosKey));
//        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Transactional(readOnly = true)
    public Member findMember(String username) {
        Member member = memberRepository.findByUsername(username).orElseGet(() -> {
            return new Member();
        });
        return member;
    }

    private String getAccessToken(String code) throws JsonProcessingException{
        String host = "https://kauth.kakao.com/oauth/token";
        String redirectUrl = "http://localhost:8080/member/login/oauth/kakao/callback";

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "0c7de4f37c334c2d42105c51f0b9ab12");
        body.add("redirect_uri", redirectUrl);
        body.add("code", code);

        // HttpHeader, HttpBody 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);

        // POST 방식으로 요청 및 응답 수신
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                host, HttpMethod.POST, kakaoTokenRequest, String.class
        );

        // 응답 정보 -> 엑세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    private KakaoUser getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        String host = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        // POST 방식으로 요청 및 응답 수신
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                host , HttpMethod.POST, kakaoUserInfoRequest, String.class
        );

        // 카카오 응답으로 온 사용자 정보 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        Long id = jsonNode.get("id").asLong();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String ageRange = jsonNode.get("kakao_account").get("age_range").asText();
        String name = jsonNode.get("properties").get("nickname").asText();
        String gender = jsonNode.get("kakao_account").get("gender").asText();

        return new KakaoUser(id, email, ageRange, name, gender);
    }

    @Transactional
    public void join(KakaoUser kakaoUser) {
        // 회원가입을 멤버가 직접하지 않고 카카오 정보로 이용해 자동 회원가입
        Member member = new Member();

//        String rawPassword = UUID.randomUUID().toString();                 // PWM의 회원 원문 비밀번호 => UUID
//        String encPassword = encoder.encode(rawPassword);                  // PWM의 비밀번호 해쉬 값 변환
        String nickname = kakaoUser.getAgeRange() + " " +
                            kakaoUser.getGender() + " " +
                            kakaoUser.getId() + "호";

        member.setName(kakaoUser.getName());
        member.setNickname(nickname);
        member.setEmail(kakaoUser.getEmail());
        member.setAgeRange(kakaoUser.getAgeRange());
        member.setMannerTemp(36);
        member.setGender(kakaoUser.getGender());
        member.setRole(RoleType.USER);

        Member saveMember = memberRepository.save(member);
    }

}
