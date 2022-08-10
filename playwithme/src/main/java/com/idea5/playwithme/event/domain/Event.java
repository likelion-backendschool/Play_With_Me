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

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    private Long category_id;

    @Column(length = 20)
    private String name;

    @Column(length = 200)
    private String location;

    @CreatedDate
    private LocalDateTime date;

//    @OneToOne(mappedBy = "event", fetch = FetchType.LAZY)
//    private Board board; // 일대일 양방향 매핑 ( 읽기 전용 )


}
