package com.idea5.playwithme.member.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "mebmer_id")
    private Long id;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    private String age_range;

    @Column(length = 20)
    private String name;

    private LocalDateTime created_at;

    private Integer manner_temp;

    @Column(length = 20)
    private String gender;

    @OneToMany(mappedBy = "member")
    private List<Article> articleList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();



}
