package com.idea5.playwithme.event.repository;

import com.idea5.playwithme.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByDateBetweenAndCategoryId(LocalDateTime start, LocalDateTime end, Integer category_id);
    List<Event> findAll();
}
