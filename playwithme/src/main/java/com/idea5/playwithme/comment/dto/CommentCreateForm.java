package com.idea5.playwithme.comment.dto;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreateForm {

    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now().withNano(0);

    private LocalDateTime updatedAt= LocalDateTime.now().withNano(0);

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String contents;

    private boolean secretStatus;

    private Article article;

    private Member member;

    /**
     * Dto - Entity
     */
    public Comment toEntity(){
            Comment comment = Comment.builder()
                    .id(id)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .contents(contents)
                    .secretStatus(secretStatus)
                    .article(article)
                    .member(member)
                    .build();
            return comment;
    }

}
