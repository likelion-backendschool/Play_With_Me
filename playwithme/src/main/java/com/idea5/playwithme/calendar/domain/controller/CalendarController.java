package com.idea5.playwithme.calendar.domain.controller;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.service.EventService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class CalendarController {
    private EventService eventService;
    private List<Event> events = new ArrayList<>();

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
    public List<Event> getEvent(Model model, @RequestParam String category, @RequestParam(defaultValue = "new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date())") String date ) {
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

        //String -> LocalDate로 파싱
        LocalDate localDateType = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        //LocalDate -> LocalDateTime으로 파싱 (시간은 00:00:00으로 들어감)
        LocalDateTime localDateTimeType = localDateType.atStartOfDay();

        events = eventService.findByCategoryIdAndDate(categoryId, localDateTimeType);

        return events;
    }
}
 // 단일 데이터 event는 JSON 헝태로 잘 전송됨
    /*
    @GetMapping("/getEvent")
    @ResponseBody
    public Event getEvent(Model model, @RequestParam String category, @RequestParam(defaultValue = "new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date())") String date ) {
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
        Event event = new Event();
        event.setId(3L);
        event.setCategoryId(1);
        event.setName("KBL경기");
        event.setLocation("서울");
        event.setDate(LocalDateTime.of(2022,8,15,00,00,00));
        return event;
    }
    */





