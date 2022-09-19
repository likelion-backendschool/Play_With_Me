package com.idea5.playwithme.member.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.mypage.domain.Timeline;
import com.idea5.playwithme.together.domain.Together;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String username;        // 카카오 정보 식별을 위한 key

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 50)
    private String nickname;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    private String ageRange;

    @CreatedDate
    private LocalDateTime createdAt;

    private Float mannerTemp;

    @Column(length = 20)
    private String gender;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Article> articleList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Together> togetherList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Timeline> timelineList = new ArrayList<>();

    // 매너 온도 수정
    public void modify(float score) {
        this.mannerTemp += score;
    }
}
