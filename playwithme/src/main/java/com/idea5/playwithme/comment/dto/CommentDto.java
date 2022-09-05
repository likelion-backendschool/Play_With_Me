package com.idea5.playwithme.comment.dto;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String contents;

    private boolean secretStatus;

    private Article article;

    private Member member;

    private Comment parent;

    private List<Comment> childList = new ArrayList<>();



}
