package com.idea5.playwithme.event.domain.repository;

import com.idea5.playwithme.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findByCategoryId(Integer id);
    List<Event> findByCategoryIdAndDate(Integer categoryId, LocalDateTime date);
    List<Event> findAllByDateBetweenAndCategoryId(LocalDateTime start, LocalDateTime end, Integer categoryId);
}
