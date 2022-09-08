package com.idea5.playwithme.timeline.domain;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.together.domain.Together;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Timeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeline_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 멤버 다대일
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 투게더 일대일
    @OneToOne
    @JoinColumn(name = "together_id")
    private Together together;

    public void update(String memo) {
        this.memo = memo;
    }

    // 이벤트 다대일
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
