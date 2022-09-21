package com.idea5.playwithme.mypage.domain.controller;


import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.MemberInfoDTO;
import com.idea5.playwithme.member.exception.MemberNotFoundException;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.together.service.TogetherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypPageController {
    private final MemberService memberService;
    private final TogetherService togetherService;
    
    @GetMapping("")
    public String showMyPage(Model model, Principal principal) {
        // principal null 체크
        if (principal == null) {
            log.info("You are Logged out. Login or Sign in Please!");
            return "redirect:/";
        }

        Member member = null;
        try {
            member = memberService.findMember(principal.getName());
        } catch (MemberNotFoundException e) {
            log.warn("MemberNotFoundException = {}", e.getMessage());
            return "redirect:/";
        }

        String createDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(member.getCreatedAt());

        // MemberInfoDto에 Member 정보 저장
        MemberInfoDTO memberInfo = MemberInfoDTO.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .ageRange(member.getAgeRange())
                .email(member.getEmail())
                .mannerTemp(String.valueOf(member.getMannerTemp()))
                .createDate(createDate)
                .build();

        model.addAttribute("memberInfo", memberInfo);
        return "mypage";
    }

    @GetMapping("/dday")
    public String showDday(Model model, Principal principal){
        Member member = memberService.findMember(principal.getName());

        List<Event> events = togetherService.findByMemberId(member.getId());
        Map<Event,Long> map = new HashMap<>();

        for (Event event : events) {
            map.put(event, ChronoUnit.DAYS.between(LocalDateTime.now(), event.getDate()));
        }
//        model.addAttribute("events",events);
        model.addAttribute("map",map);
        return "d_day_list";
    }

}
