package com.idea5.playwithme.article.dto;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.member.domain.Member;
import lombok.*;

import java.time.LocalDateTime;
// TODO : 삭제하기(form 으로 대체)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDto {

    private Long id;
    private String title;
    private String contents;
    private Integer maxRecruitNum;
    private Boolean recruitStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String gender;
    private String ageRange;
    private Board board;
    private Member member;

    // ArticleDto -> Article 변환
    public static Article toEntity(ArticleRequestDto articleDto) {
        return Article.builder()
                .id(articleDto.getId())
                .title(articleDto.getTitle())
                .contents(articleDto.getContents())
                .maxRecruitNum(articleDto.getMaxRecruitNum())
                .recruitStatus(articleDto.getRecruitStatus())
                .gender(articleDto.getGender())
                .ageRange(articleDto.getAgeRange())
                .board(articleDto.getBoard())
                .member(articleDto.getMember())
                .build();
    }
}
