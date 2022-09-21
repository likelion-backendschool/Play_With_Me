package com.idea5.playwithme.together.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Together {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "together_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @CreatedDate
    private LocalDateTime createdAt;

    // 양방향 연관관계 메서드
    public void setEvent(Event event){
        if (this.event != null)
            this.event.getTogethers().remove(this);

        this.event = event;
        event.getTogethers().add(this);
    }

//    @OneToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "timeline_id")
//    private Timeline timeline; // 읽기 전용

}

