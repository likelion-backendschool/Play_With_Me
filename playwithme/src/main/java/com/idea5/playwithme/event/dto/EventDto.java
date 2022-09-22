package com.idea5.playwithme.event.dto;

import com.idea5.playwithme.event.domain.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventDto {

    private Integer categoryId;

    private String name;

    private String location;

    private LocalDateTime date;

    public static EventDto getDtoFromEntity(Event event){
        return EventDto.builder()
                .categoryId(event.getCategoryId())
                .name(event.getName())
                .location(event.getLocation())
                .date(event.getDate())
                .build();
    }
}
