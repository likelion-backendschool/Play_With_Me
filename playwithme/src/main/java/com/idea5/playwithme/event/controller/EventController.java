package com.idea5.playwithme.event.controller;

import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.service.BoardService;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;

import com.idea5.playwithme.event.service.crawling.ConcertAndMusicalCrawlService;
import com.idea5.playwithme.event.service.crawling.SportsCrawlService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
public class EventController {
    private ConcertAndMusicalCrawlService concertAndMusicalCrawlService;
    private SportsCrawlService sportsCrawlService;
    private EventService eventService;

    private List<Event> events = new ArrayList<>();
    private final BoardService boardService;


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

    @GetMapping("/all")
    @ResponseBody
    public List<Event> getAllEvents(){
        List<Event> list = eventService.getAllEvents();
        return list;
    }

    @GetMapping("/event")
    public String showEvent(Model model, @RequestParam String category, @RequestParam(defaultValue = "new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date())") String date) {
        Integer categoryId = 0;
        switch (category) {
            case "baseball":
                categoryId = 1;
                break;
            case "soccer":
                categoryId = 2;
                break;
            case "basketball":
                categoryId = 3;
                break;
            case "musical":
                categoryId = 4;
                break;
            case "concert":
                categoryId = 5;
                break;
        }

        events = eventService.findByCategoryId(categoryId);

        model.addAttribute("events", events);

        return "calendar";
    }

    // 캘린더 날짜 클릭시 ajax로 category(->categoryId)와 date(->localDateTimeType) 조건에 해당하는 Event List 리턴
    @GetMapping("/getEvent")
    @ResponseBody
    public List<Event> getEvent(Model model, @RequestParam String category,  @RequestParam(defaultValue = "0")String date ) {
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
        switch (category) {
            case "baseball":
                categoryId = 1;
                break;
            case "soccer":
                categoryId = 2;
                break;
            case "basketball":
                categoryId = 3;
                break;
            case "musical":
                categoryId = 4;
                break;
            case "concert":
                categoryId = 5;
                break;
        }

        events = eventService.getEventsByCategoryAndDate(categoryId, searchDate);

        return events;
    }



    // 각 이벤트 카드 클릭시 ajax로 카테고리, 날짜, JS의 event_card_index(해당 날짜와 카테고리에서 몇번째 인덱스인지) 조건에 해당하는 boardId 리턴
    @GetMapping("/getBoardId")
    @ResponseBody
    public Long getBoardIdByJsIndex (Model model, @RequestParam int index, @RequestParam String category,  @RequestParam(defaultValue = "0")String date) {
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
        switch (category) {
            case "baseball":
                categoryId = 1;
                break;
            case "soccer":
                categoryId = 2;
                break;
            case "basketball":
                categoryId = 3;
                break;
            case "musical":
                categoryId = 4;
                break;
            case "concert":
                categoryId = 5;
                break;
        }

        events = eventService.getEventsByCategoryAndDate(categoryId, searchDate);

        // 해당 날짜와 카테고리에서 몇번째 인덱스(=event_card_index)인지
        Event event =events.get(index);

        // board id == event id
        Long boardId = event.getId();

        return boardId;
    }

    // 전체 게시글 조회로 redirect
    @GetMapping("/event/board")
    public String getBoard (@RequestParam("id")Long id){
        Long boardId = id;

        return  "redirect:/board/%d".formatted(boardId);
    }

}





