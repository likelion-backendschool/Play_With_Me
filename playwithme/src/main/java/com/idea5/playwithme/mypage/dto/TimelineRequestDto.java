package com.idea5.playwithme.mypage.dto;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.mypage.domain.Timeline;
import com.idea5.playwithme.together.domain.Together;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

// AnswerController.modify()에서 answerForm.setContent 필요
@Setter
@Getter
public class TimelineRequestDto {
    private Long id;

    @NotEmpty(message = "내용을 입력해주세요")
    private String memo;

    private LocalDateTime createdAt = LocalDateTime.now().withNano(0);

    private LocalDateTime updatedAt= LocalDateTime.now().withNano(0);

    private Member member;

    private Together together;

    private Event event;


    // DTO -> Entity 변환
    public Timeline toEntity(){
        Timeline timeline = Timeline.builder()
                .id(id)
                .memo(memo)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .member(member)
                .together(together)
                .event(event)
                .build();
        return timeline;
    }

}
