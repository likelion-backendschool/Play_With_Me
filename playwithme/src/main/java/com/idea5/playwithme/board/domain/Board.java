package com.idea5.playwithme.board.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.event.domain.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @CreatedDate
    private LocalDateTime created_at;
    // 카멜케이스
    private boolean is_blind;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event Event;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Article> articleList = new ArrayList<>();


}
