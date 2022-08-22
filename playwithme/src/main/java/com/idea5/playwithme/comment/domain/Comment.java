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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    //== 부모 댓글을 삭제해도 자식 댓글은 남아있음 ==//
    @OneToMany(mappedBy = "parent")
    private List<Comment> childList = new ArrayList<>();



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
