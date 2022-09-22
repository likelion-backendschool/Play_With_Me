package com.idea5.playwithme.article.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.dto.ArticleUpdateForm;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.repository.BoardRepository;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.repository.EventRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

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
    @Autowired
    private EventRepository eventRepository;

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
    void createSampleData() {
        Event event = Event.builder()
                .categoryId(1)
                .date(LocalDateTime.now())
                .location("잠실종합운동장 내 보조경기장")
                .name("싸이 흠뻑쇼 SUMMER SWAG - 서울")
                .build();
//        eventRepository.save(event);

        Board board = Board.builder()
                .createdAt(LocalDateTime.now())
                .isBlind(false)
                .event(event)
                .build();
        board.setEvent(event);
        boardRepository.save(board);

//        Member member = Member.builder()
//                .createdAt(LocalDateTime.now())
//                .ageRange("10~19")
//                .email("user1@test.com")
//                .gender("Female")
//                .mannerTemp(100F)
//                .name("강해린")
//                .build();
//        memberRepository.save(member);
        Member member = memberRepository.findById(1L).orElse(null);

        String[] genders = {"여성", "남성", "성별무관"};
        String[] minAge = {"10", "20", "30", "40", "50"};
        String[] maxAge = {"19", "29", "39", "49", "59"};

        IntStream.rangeClosed(1, 20).forEach(id -> {
            ArticleCreateForm articleCreateForm = ArticleCreateForm.builder()
                    .title("제목 %d".formatted(id))
                    .contents("내용 %d".formatted(id))
                    .maxRecruitNum(Integer.toString(id % 5 + 1))
                    .gender(genders[id % 3])
                    .minAge(minAge[id % 5])
                    .maxAge(maxAge[id % 5])
                    .build();
            articleService.create(1L, articleCreateForm, member);
        });
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
                .maxRecruitNum("5")
                .gender("Female")
                .minAge("10")
                .maxAge("20")
                .build();
        // when
        articleService.create(1L, articleCreateForm, member);
        // then
        Article findArticle = articleRepository.findById(1L).orElse(null);
        assertThat(findArticle.getTitle()).isEqualTo("제목");
        assertThat(findArticle.getContents()).isEqualTo("내용");
        assertThat(findArticle.getMaxRecruitNum()).isEqualTo(5);
        assertThat(findArticle.getRecruitStatus()).isEqualTo(true);
        assertThat(findArticle.getGender()).isEqualTo("Female");
        assertThat(findArticle.getAgeRange()).isEqualTo("10~20");
    }

    @Test
    void update() {
        ArticleUpdateForm articleUpdateForm = ArticleUpdateForm.builder()
                .title("제목!!")
                .contents("내용!!")
                .maxRecruitNum("10")
                .gender("Male")
                .minAge("30")
                .maxAge("40")
                .build();
        Long articleId = 1L;
        // when
        articleService.update(articleId, articleUpdateForm);
        // then
        Article findArticle = articleRepository.findById(articleId).orElse(null);
        assertThat(findArticle.getTitle()).isEqualTo("제목!!");
        assertThat(findArticle.getContents()).isEqualTo("내용!!");
        assertThat(findArticle.getMaxRecruitNum()).isEqualTo("10");
        assertThat(findArticle.getGender()).isEqualTo("Male");
        assertThat(findArticle.getAgeRange()).isEqualTo("30~40");
        assertThat(findArticle.getUpdatedAt()).isAfter(findArticle.getCreatedAt());
    }
}