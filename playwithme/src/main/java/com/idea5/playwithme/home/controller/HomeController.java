package com.idea5.playwithme.home.controller;

import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.service.BoardService;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import com.idea5.playwithme.member.domain.Member;
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
    public String home(Model model, Principal principal) { // TODO : 중복 제거 리팩토링

        Event baseballTop = eventService.findTopEventByArticleCount(1);
        Event soccerTop = eventService.findTopEventByArticleCount(2);
        Event basketballTop = eventService.findTopEventByArticleCount(3);
        Event musicalTop = eventService.findTopEventByArticleCount(4);
        Event concertTop = eventService.findTopEventByArticleCount(5);

        Board baseballTopBoard = baseballTop!=null? boardService.findByEvent_Id(baseballTop.getId()):null;
        Board soccerTopBoard = soccerTop!=null? boardService.findByEvent_Id(soccerTop.getId()):null;
        Board basketballTopBoard = basketballTop!=null? boardService.findByEvent_Id(basketballTop.getId()):null;
        Board musicalTopBoard = musicalTop!=null? boardService.findByEvent_Id(musicalTop.getId()):null;
        Board concertTopBoard = concertTop!=null? boardService.findByEvent_Id(concertTop.getId()):null;

        model.addAttribute("baseballTop",baseballTop);
        model.addAttribute("soccerTop", soccerTop);
        model.addAttribute("basketballTop", basketballTop);
        model.addAttribute("musicalTop", musicalTop);
        model.addAttribute("concertTop", concertTop);

        model.addAttribute("baseballTopBoard",baseballTopBoard);
        model.addAttribute("soccerTopBoard", soccerTopBoard);
        model.addAttribute("basketballTopBoard", basketballTopBoard);
        model.addAttribute("musicalTopBoard", musicalTopBoard);
        model.addAttribute("concertTopBoard", concertTopBoard);
        Long count = eventService.countEventAfterNow();

        System.out.println("asd "+count);

        if(principal!=null){
            Member member = memberService.findMember(principal.getName());

            List<Together> togethers = togetherService.findByMemberId(member.getId());
            String check = "empty";
            if (togethers == null || togethers.size()==0)  {
                List<Event> events = new ArrayList<>();

                if(baseballTop!=null){
                    events.add(baseballTop);
                }if(soccerTop!=null){
                    events.add(soccerTop);
                }if(basketballTop!=null){
                    events.add(basketballTop);
                }if(musicalTop!=null){
                    events.add(musicalTop);
                }if(concertTop!=null){
                    events.add(concertTop);
                }

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
            model.addAttribute("count",count);
            model.addAttribute("check",check);
        }

        return "home";
    }
}
