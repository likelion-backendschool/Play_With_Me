package com.idea5.playwithme.comment.dto;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {

    private Long id;

    private String nickName;
    private LocalDateTime updatedAt= LocalDateTime.now().withNano(0);
    private String contents;

    private boolean secretStatus;

    private Long articleId;

    /**
     * Entity -> Dto
     */
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.nickName = comment.getMember().getName();
        this.contents = comment.getContents();
        this.updatedAt = comment.getUpdatedAt();
        this.secretStatus = comment.getSecretStatus();
        this.articleId = comment.getArticle().getId();
    }

}
