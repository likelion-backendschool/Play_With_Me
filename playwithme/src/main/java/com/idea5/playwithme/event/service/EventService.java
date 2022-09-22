package com.idea5.playwithme.event.service;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.repository.EventRepository;
import com.idea5.playwithme.mypage.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public Page<Event> getList(String kw, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));

        PageRequest pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return eventRepository.findByNameContainsOrLocationContains(kw, kw,pageable);
    }

    public Event findTopEventByArticleCount(Integer categoryNo){
        return eventRepository.findTopEventByArticleCount(categoryNo);
    }

    public Long countEventAfterNow(){
        return eventRepository.countByDateAfter(LocalDateTime.now());
    }
}

