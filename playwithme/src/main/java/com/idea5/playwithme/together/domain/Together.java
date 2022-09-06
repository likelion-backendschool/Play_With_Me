package com.idea5.playwithme.together.domain;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Together {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "together_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

