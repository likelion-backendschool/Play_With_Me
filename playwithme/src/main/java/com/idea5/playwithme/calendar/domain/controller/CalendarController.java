package com.idea5.playwithme.calendar.domain.controller;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.service.EventService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class CalendarController {
    private EventService eventService;
    private List<Event> events = new ArrayList<>();

    @GetMapping("/event") // 템플릿 적용 확인 테스트용
    public String showCalendar(){ // 템플릿 적용 확인 테스트용
        return "calendar";
    }

    @GetMapping("/event/{category}")
    public String showEvent(Model model, @PathVariable String category) {
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

        events = eventService.findByCategoryId(categoryId);

        model.addAttribute("events", events);

        return "calendar";
    }





}




