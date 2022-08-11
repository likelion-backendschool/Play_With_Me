package com.idea5.playwithme.event.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private Long categoryId;

    @Column(length = 20)
    private String name;

    @Column(length = 200)
    private String location;

    @CreatedDate
    private LocalDateTime date;

//    @OneToOne(mappedBy = "event", fetch = FetchType.LAZY)
//    private Board board; // 일대일 양방향 매핑 ( 읽기 전용 )


}
