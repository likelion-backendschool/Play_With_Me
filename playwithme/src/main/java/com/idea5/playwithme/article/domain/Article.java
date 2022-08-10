package com.idea5.playwithme.article.domain;

import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private int maxRecruitNum;

    private boolean recruitStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(length = 20)
    private String gender;

    @Column(length = 20)
    private String ageRange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // V
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();


}
