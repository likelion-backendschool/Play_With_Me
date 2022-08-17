package com.idea5.playwithme.event.controller;


import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import com.idea5.playwithme.event.service.crawling.ConcertAndMusicalCrawlService;
import com.idea5.playwithme.event.service.crawling.SportsCrawlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/event")
public class EventController {

    private EventService eventService;
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

    @GetMapping("/{category}")
    @ResponseBody
    public List<Event> getEvents(@PathVariable("category")String category, @RequestParam(defaultValue = "0")String date){
        LocalDate searchDate;

        if(date.equals("0")){ // 아무것도 입력하지 않았을 때는 당일 날짜로 고정
            searchDate = LocalDate.now();
        }
        else{ // 입력한 String형의 데이터를 localdate로 변환
            int [] dateInfo = Arrays.stream(date.split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            searchDate =  LocalDate.of(dateInfo[0],dateInfo[1],dateInfo[2]);
        }


        Integer categoryId = 0;
        switch(category) {
            case "baseball": categoryId = 1;
                break;
            case "soccer": categoryId = 2;
                break;
            case "basketball": categoryId = 3;
                break;
            case "musical": categoryId = 4;
                break;
            case "concert": categoryId = 5;
                break;
        }

        if(categoryId == 0){ //category 잘 못 입력하거나 입력하지 않았을 떄 오류 처리
            //TODO 오류 처리 코드
        }

        List<Event> events = eventService.getEventsByCategoryAndDate(categoryId, searchDate);
        return events;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Event> getAllEvents(){
        List<Event> list = eventService.getAllEvents();
        return list;
    }
}
