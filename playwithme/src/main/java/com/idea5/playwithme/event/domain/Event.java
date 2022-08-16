package com.idea5.playwithme.event.domain;

import com.idea5.playwithme.board.domain.Board;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private Integer categoryId;

    @Column(length = 100)
    private String name;

    @Column(length = 200)
    private String location;

    @CreatedDate
    private LocalDateTime date;


//    @OneToOne(mappedBy = "event", fetch = FetchType.LAZY)
//    private Board board; // 일대일 양방향 매핑 ( 읽기 전용 )


}
