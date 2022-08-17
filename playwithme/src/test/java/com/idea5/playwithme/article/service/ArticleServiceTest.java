package com.idea5.playwithme.article.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.domain.repository.BoardRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.domain.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {

    }

    @Test
    void getDetails() {
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
        // given
        Board board = Board.builder()
                .createdAt(LocalDateTime.now())
                .isBlind(true)
                .build();
        boardRepository.save(board);
        Member member = new Member();
        memberRepository.save(member);
        ArticleCreateForm articleCreateForm = ArticleCreateForm.builder()
                .title("제목")
                .contents("내용")
                .maxRecruitNum(5)
                .gender("Female")
                .ageRange("10~20")
                .build();
        // when
        articleService.create(1L, articleCreateForm);
        // then
        Article findArticle = articleRepository.findById(1L).orElse(null);
        assertThat(findArticle.getTitle()).isEqualTo("제목");
        assertThat(findArticle.getContents()).isEqualTo("내용");
        assertThat(findArticle.getMaxRecruitNum()).isEqualTo(5);
        assertThat(findArticle.getRecruitStatus()).isEqualTo(true);
        assertThat(findArticle.getGender()).isEqualTo("Female");
        assertThat(findArticle.getAgeRange()).isEqualTo("10~20");
    }
}