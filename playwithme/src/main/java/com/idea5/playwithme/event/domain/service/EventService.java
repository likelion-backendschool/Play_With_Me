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

    public void create(Long id, Integer categoryId, String name, String location, LocalDateTime date) { // getEvent_Test()용
        Event e = new Event();
        e.setId(id);
        e.setCategoryId(categoryId);
        e.setName(name);
        e.setLocation(location);
        e.setDate(date);
        eventRepository.save(e);
    }
}

