package com.idea5.playwithme.calendar.domain.controller;


import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.repository.EventRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class CalendarController {
    private EventRepository eventRepository;
    private List<Event> events = new ArrayList<>();

    @GetMapping("/calendar") // 템플릿 적용 확인 테스트용
    public String showCalendar(){ // 템플릿 적용 확인 테스트용
        return "calendar";
    }

    @GetMapping("/event/{category}")
    public String showEvent(Model model, @PathVariable String category) {
        long categoryId = 0;
        switch(category) {
            case "야구": categoryId = 1;
                break;
            case "축구": categoryId = 2;
                break;
            case "농구": categoryId = 3;
                break;
            case "e-sports": categoryId = 4;
                break;
            case "musical": categoryId = 5;
                break;
            case "concert": categoryId = 6;
                break;
        }

        events = eventRepository.findByCategoryId(categoryId);

        model.addAttribute("events", events);

        return "calendar";
    }





}




