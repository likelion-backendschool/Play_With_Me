package com.idea5.playwithme;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CalendarControllerTests {
    @Autowired
    private EventService eventService;
    private List<Event> events = new ArrayList<>();

    // cateogry와 StringDate가 타입 변환 잘 돼서 Event List 조건에 맞게 리턴하는지 -> OK
    @Test
    void getEvent_Test() {
        eventService.create(1L,1,"프로야구경기","서울",LocalDateTime.of(2022,8,15,00,00,00));
        eventService.create(2L,1,"또또야구","서울",LocalDateTime.of(2022,7,10,00,00,00));

        String category = "baseball";
        String stringDate="2022-08-15";

        Integer categoryId = 0;
        switch (category) {
            case "baseball":
                categoryId = 1;
                break;
            case "soccer":
                categoryId = 2;
                break;
            case "basketball":
                categoryId = 3;
                break;
            case "musical":
                categoryId = 4;
                break;
            case "concert":
                categoryId = 5;
                break;
        }

        //String -> LocalDate로 파싱
        LocalDate localDateType = LocalDate.parse(stringDate, DateTimeFormatter.ISO_DATE);
        //LocalDate -> LocalDateTime으로 파싱
        LocalDateTime localDateTimeType = localDateType.atStartOfDay();

        events = eventService.findByCategoryIdAndDate(categoryId, localDateTimeType);
        assertThat(events.size()).isEqualTo(1);
    }
}