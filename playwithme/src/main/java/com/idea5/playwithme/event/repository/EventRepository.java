package com.idea5.playwithme.event.repository;

import com.idea5.playwithme.event.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {
    List<Event> findAll();
    List<Event> findByCategoryId(Integer id);
    List<Event> findAllByDateBetweenAndCategoryId(LocalDateTime start, LocalDateTime end, Integer categoryId);
    Page<Event> findByNameContainsOrLocationContains(String kw, String kw_,Pageable pageable);
}

