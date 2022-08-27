package com.idea5.playwithme.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.domain.MemberRole;
import com.idea5.playwithme.member.dto.KakaoUser;


import com.idea5.playwithme.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String getAccessToken(String code) throws JsonProcessingException {
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

        log.info("엑세스 토큰 가져오기 완료");
        return jsonNode.get("access_token").asText();
    }

    public KakaoUser getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        // POST 방식으로 요청 및 응답 수신
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                reqURL, HttpMethod.POST, kakaoUserInfoRequest, String.class
        );

        // 카카오 응답으로 온 사용자 정보 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        Long id = jsonNode.get("id").asLong();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String ageRange = jsonNode.get("kakao_account").get("age_range").asText();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        String gender = jsonNode.get("kakao_account").get("gender").asText();

        log.info("카카오 정보 가져오기 완료");
        return new KakaoUser(id, email, ageRange, nickname, gender);
    }

    public void kakaoLogout(String accessToken) throws JsonProcessingException {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        // HttpHeader 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        // POST 방식으로 요청 및 응답 수신
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                reqURL, HttpMethod.POST, kakaoUserInfoRequest, String.class
        );

        // 응답 정보
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String logoutId = jsonNode.get("id").asText();

        log.info("logout id : " + logoutId);
    }

    @Transactional
    public Member join(KakaoUser kakaoUser) {
        log.info("회원가입 진행중...");
        Member member = new Member();

        // Member 값 생성
        String username = kakaoUser.getEmail() + "_" + kakaoUser.getId();
        String password = kakaoUser.getEmail() + kakaoUser.getGender() + kakaoUser.getId();
        String nickname = null;
        if (kakaoUser.getGender().equals("male")) {
            nickname = "남자 " + kakaoUser.getId() + "호";
        } else {
            nickname = "여자 " + kakaoUser.getId() + "호";
        }

        // Member 초기화
        member.setName(kakaoUser.getNickname());
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));       // 암호화
        member.setNickname(nickname);
        member.setEmail(kakaoUser.getEmail());
        member.setAgeRange(kakaoUser.getAgeRange());
        member.setCreatedAt(LocalDateTime.now());
        member.setMannerTemp(36);
        member.setGender(kakaoUser.getGender());
        member.setRole(MemberRole.USER);

        return memberRepository.save(member);
    }

    public void kakaoLogin(Member member) {
        log.info("로그인 진행...");

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("ROLE_ADMIN".equals(member.getRole())) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        log.info("authorities" + authorities);

        // Security Authentication 설정
        User user = new User(member.getUsername(), member.getPassword(), authorities);
        log.info("user : " + user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        log.info("authentication : " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Member findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> {
            log.warn("Member Not Found...");
            throw new MemberNotFoundException("멤버가 없습니다.");
        });
        return member;
    }

    public Member findMember(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> {
            log.warn("Member Not Found...");
            throw new MemberNotFoundException("멤버가 없습니다.");
        });
        return member;
    }

}
