package com.idea5.playwithme.event.repository;

import com.idea5.playwithme.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
