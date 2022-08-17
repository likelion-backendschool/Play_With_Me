package com.idea5.playwithme.event.service;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsByCategoryAndDate(Integer categoryId) {
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<Event> events = eventRepository.findAllByDateBetweenAndCategoryId(start, end, categoryId);
        return events;
    }

    public List<Event> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return events;
    }
}
