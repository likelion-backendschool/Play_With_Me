package com.idea5.playwithme.comment.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String contents;

    private boolean secretStatus;

    private Comment parent;



}
