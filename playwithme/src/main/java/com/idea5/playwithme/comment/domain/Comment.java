package com.idea5.playwithme.comment.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(name = "secret_status", nullable = false)
    private Boolean secretStatus = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * 댓글 수정 메소드
     * 수정 사항
     */
    public void update(String contents) {
        this.contents = contents;
        updatedAt = LocalDateTime.now().withNano(0);
    }

    public CommentDto toCommentDto(){

        CommentDto commentDto = CommentDto.builder()
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .contents(contents)
                .secretStatus(secretStatus)
                .build();
        return commentDto;

    }


}
