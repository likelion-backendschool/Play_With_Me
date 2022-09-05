package com.idea5.playwithme.event.service;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.repository.EventRepository;
import com.idea5.playwithme.member.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return events;
    }

    public List<Event> findByCategoryId(Integer id) {
        return eventRepository.findByCategoryId(id);

    }

    public List<Event> getEventsByCategoryAndDate(Integer categoryId, LocalDate searchDate) {
        LocalDateTime start = LocalDateTime.of(searchDate, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(searchDate, LocalTime.MAX);
        List<Event> events = eventRepository.findAllByDateBetweenAndCategoryId(start, end, categoryId);
        return events;
    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("no %d question not found,".formatted(id)));
    }
}

