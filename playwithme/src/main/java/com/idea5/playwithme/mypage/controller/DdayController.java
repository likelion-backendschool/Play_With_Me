package com.idea5.playwithme.mypage.controller;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.service.TogetherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class DdayController {

    private final TogetherService togetherService;
    private final MemberService memberService;

    @GetMapping("/dday")
    public String showDday(Model model, Principal principal){
        Member member = memberService.findMember(principal.getName());

        List<Event> events = togetherService.findByMemberId(member.getId());
        Map<Event,Long> map = new HashMap<>();

        for (Event event : events) {
            map.put(event,ChronoUnit.DAYS.between(LocalDateTime.now(), event.getDate()));
        }
//        model.addAttribute("events",events);
        model.addAttribute("map",map);
        return "d_day_list";
    }
}
