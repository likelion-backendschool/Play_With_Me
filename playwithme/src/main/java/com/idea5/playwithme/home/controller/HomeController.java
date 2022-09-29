package com.idea5.playwithme.home.controller;

import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.service.BoardService;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.MemberInfoDTO;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.service.TogetherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class HomeController {
    private final EventService eventService;
    private final BoardService boardService;
    private final TogetherService togetherService;
    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        List<Event> events = new ArrayList<>();
        List<String> categories = new ArrayList<String>(Arrays.asList("Baseball", "Soccer", "Basketball", "Musical", "Concert"));

        int i;
        for (i = 0; i < categories.size(); i++) {
            // 카테고리별 인기 이벤트 리턴 (인기도 판별기준: 게시글 수)
            Event topEvent = eventService.findTopEventByArticleCount(i+1); // categoryNo-> 1(baseball), 2(soccer), 3(basketball), 4(musical), 5(concert)
            model.addAttribute(  "top" + categories.get(i), topEvent);

            events.add(topEvent);

            if (topEvent != null) {
                // 인기 이벤트의 게시판 리턴
                Board topBoard = boardService.findByEvent_Id(topEvent.getId());
                model.addAttribute("top"+ categories.get(i) + "Board", topBoard);

            } else {
                model.addAttribute("top"+ categories.get(i) + "Board", null);
            }
        }

        Long count = eventService.countEventAfterNow();

        System.out.println("asd "+count);

        if(principal!=null){
            Member member = memberService.findMember(principal.getName());
            MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();
            model.addAttribute("memberInfo", memberInfo);


            List<Together> togethers = togetherService.findByMemberId(member.getId());
            String check = "empty";
            if (togethers == null || togethers.size()==0)  {

//                model.addAttribute("first",events.get(0));
//                events.remove(0);
//                model.addAttribute("events",events);
            }
            else{
                check = "full";
                model.addAttribute("first",togethers.get(0));
                togethers.remove(0);
                model.addAttribute("togethers",togethers);
            }

            model.addAttribute("member", member);
            model.addAttribute("count",count);
            model.addAttribute("check",check);
        }

        return "home";
    }

    // 소개 페이지
    @GetMapping("/about")
    public String intro(Model model, Principal principal) {
        if (principal != null && principal.getName().length()!=0) {
            Member member = memberService.findMember(principal.getName());
            MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();
            model.addAttribute("memberInfo", memberInfo);
        }

        return "about";
    }
}
