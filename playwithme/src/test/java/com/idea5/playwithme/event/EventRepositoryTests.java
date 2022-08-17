package com.idea5.playwithme.event;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EventRepositoryTests {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void findAllByDateBetweenTest(){
        LocalDateTime startTest = LocalDateTime.of(LocalDate.of(2022,9,17), LocalTime.MIN);
        LocalDateTime endTest = LocalDateTime.of(LocalDate.of(2022,9,17), LocalTime.MAX);

        List<Event> events = eventRepository.findAllByDateBetweenAndCategoryId(startTest, endTest,1);

        assertThat(events.size()).isEqualTo(5);
    }
}
