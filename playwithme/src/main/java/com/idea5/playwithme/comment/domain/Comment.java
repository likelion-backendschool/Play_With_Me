package com.idea5.playwithme.comment.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.dto.CommentDto;
import com.idea5.playwithme.member.domain.Member;
import lombok.*;
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

    /**
     *  * Todo
     *  * child List들 정렬해서 보내주면 되지 않을까
     */
    //== 부모 댓글을 삭제해도 자식 댓글은 남아있음 ==//
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> childList = new ArrayList<>();



    /**
     * 댓글 수정 메소드
     * 수정 사항
     */
    public void update(String contents, Boolean secretStatus) {
        this.contents = contents;
        this.secretStatus =secretStatus;
        updatedAt = LocalDateTime.now().withNano(0);
    }

    /**
     * 연관관계 편의 메소드
     */
//    public void confirmWriter(Member writer) {
//        this.member= writer;
//        writer.addComment(this);
//    }

    public void confirmArticle(Article article) {
        this.article = article;
        article.addComment(this);
    }

    public void confirmParent(Comment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child){
        childList.add(child);
    }


    public CommentDto toCommentDto(){

        CommentDto commentDto = CommentDto.builder()
                .id(id)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .contents(contents)
                .secretStatus(secretStatus)
                .parent(parent)
                .member(member)
                .childList(childList)
                .article(article)
                .build();

        return commentDto;

    }


}
