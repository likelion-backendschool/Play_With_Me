package com.idea5.playwithme.home.controller;

import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.service.BoardService;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class HomeController {
    private final EventService eventService;
    private final BoardService boardService;

    @GetMapping("/")
    public String home(Model model) { // TODO : 중복 제거 리팩토링
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


        return "home";
    }
}
