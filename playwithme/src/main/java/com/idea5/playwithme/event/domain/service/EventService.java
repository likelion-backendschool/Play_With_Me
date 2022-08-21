package com.idea5.playwithme.event.domain.service;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    public List<Event> findByCategoryId(Integer id) {
        return eventRepository.findByCategoryId(id);

    }

    public List<Event> findByCategoryIdAndDate(Integer categoryId, LocalDateTime date)
    {
        return eventRepository.findByCategoryIdAndDate(categoryId, date);
    }

}

