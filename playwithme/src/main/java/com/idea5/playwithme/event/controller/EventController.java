package com.idea5.playwithme.event.controller;


import com.idea5.playwithme.event.service.crawling.ConcertAndMusicalCrawlService;
import com.idea5.playwithme.event.service.crawling.SportsCrawlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
public class EventController {


    private ConcertAndMusicalCrawlService concertAndMusicalCrawlService;
    private SportsCrawlService sportsCrawlService;

    @GetMapping("/crawl")
    @ResponseBody
    public String crawl(){

        for(int i=1;i<=3;i++){
            sportsCrawlService.saveEvent(i);
        }
        for(int i=4;i<=5;i++){
            concertAndMusicalCrawlService.saveEvent(i);
        }
        return "ok";
    }
}
