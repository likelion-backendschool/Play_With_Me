package com.idea5.playwithme.together.domain;

import com.idea5.playwithme.event.dto.EventDto;
import com.idea5.playwithme.member.dto.MemberRecruitDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TogetherInfoDto {
    private Long id;
    private EventDto eventDto;
    private List<MemberRecruitDto> members = new ArrayList<>();

    public void addMembers(MemberRecruitDto dto){
        getMembers().add(dto);
    }
}
