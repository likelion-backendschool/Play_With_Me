package com.idea5.playwithme.event;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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

    @Test
    public void 문자열을_입력받아_LocalDateTime으로_변환(){
        String input = "2022-09-18";

        int [] dateInfo = Arrays.stream(input.split("-"))
                                .mapToInt(Integer::parseInt)
                                .toArray();

        LocalDate date = LocalDate.of(dateInfo[0],dateInfo[1],dateInfo[2]);
        LocalDateTime t =  LocalDateTime.of(date, LocalTime.now());


        assertThat(t.getYear()).isEqualTo(2022);
        assertThat(t.getMonthValue()).isEqualTo(9);
        assertThat(t.getDayOfMonth()).isEqualTo(18);
    }
}
