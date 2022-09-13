package com.idea5.playwithme.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idea5.playwithme.member.repository.MemberRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.domain.MemberRole;
import com.idea5.playwithme.member.dto.KakaoUser;


import com.idea5.playwithme.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
        member.setMannerTemp(36.5F);
        member.setGender(kakaoUser.getGender());
        member.setRole(MemberRole.USER);

        return memberRepository.save(member);
    }

    public Member findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> {
            log.warn("Member Not Found...");
            throw new MemberNotFoundException("멤버가 없습니다.");
        });
        return member;
    }

    public Member findMember(String username) {
        return memberRepository.findByUsername(username).orElseThrow(() -> {
            log.warn("Member Not Found...");
            throw new MemberNotFoundException("멤버가 없습니다.");
        });
    }
}
