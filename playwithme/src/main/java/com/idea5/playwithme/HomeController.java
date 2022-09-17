package com.idea5.playwithme;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.together.service.TogetherService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final TogetherService togetherService;
    private final MemberService memberService;
    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("data", "홈 화면입니다.");
        if(principal!=null){
            Member member = memberService.findMember(principal.getName());

            List<Event> events = togetherService.findByMemberId(member.getId());
            System.out.println("asd "+ events.size());
            model.addAttribute("events",events);
        }


        return "home";
    }
}
