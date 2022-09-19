package com.idea5.playwithme.together.domain;

import com.idea5.playwithme.article.dto.ArticleDto;
import com.idea5.playwithme.event.dto.EventDto;
import com.idea5.playwithme.member.dto.MemberRecruitDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TogetherInfoDto {
    private Long id;
    private ArticleDto articleDto;
    private EventDto eventDto;
    private LocalDateTime createdAt;
    private List<MemberRecruitDto> members = new ArrayList<>();
    private Long remainDays;
    private Boolean isCancel;


    public void addMembers(MemberRecruitDto dto){
        getMembers().add(dto);
    }
}
