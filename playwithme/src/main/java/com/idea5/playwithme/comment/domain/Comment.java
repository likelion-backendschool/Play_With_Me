package com.idea5.playwithme.comment.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.member.domain.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    private Integer groupNumber; // 댓글 그룹

    @ColumnDefault("0")
    private Integer step; //대댓글의 계층 ( 0 : 부모댓글, 1 : 자식댓글 )

    @ColumnDefault("0")
    private Integer groupOrder; // 대댓글 간에 순서



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
