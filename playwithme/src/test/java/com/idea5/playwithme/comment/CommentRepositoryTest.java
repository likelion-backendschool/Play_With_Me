package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.domain.repository.BoardRepository;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.domain.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ArticleService articleService;

//    @BeforeEach
    @BeforeEach
    public void beforeEach(){
        commentRepository.deleteAll();
        articleRepository.deleteAll();
        memberRepository.deleteAll();
        boardRepository.deleteAll();
        Member member1 = new Member();
        member1.setName("memberA");

        Member member2 = new Member();
        member2.setName("memberB");

        memberRepository.save(member1);
        memberRepository.save(member2);


        // given
        Board board = Board.builder()
                .createdAt(LocalDateTime.now())
                .isBlind(true)
                .build();
        boardRepository.save(board);
        Member member = new Member();
        member.setName("memberC");

        memberRepository.save(member);
        ArticleCreateForm articleCreateForm = ArticleCreateForm.builder()
                .title("제목")
                .contents("내용")
                .maxRecruitNum(5)
                .gender("Female")
                .ageRange("10~20")
                .build();
        Article article = ArticleCreateForm.toEntity(articleCreateForm);
        article.setMember(member1);
        // when
        articleRepository.save(article);


        Comment comment = new Comment();
        comment.setContents("test");
        comment.setSecretStatus(false);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setArticle(article);
        comment.setMember(member1);
        commentRepository.save(comment);

        Comment comment2 = new Comment();
        comment2.setContents("test2");
        comment2.setSecretStatus(true);
        comment2.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment2.setArticle(article);
        comment2.setMember(member2);
        commentRepository.save(comment2);

    }

    @Test
    public void 테스트(){

    }


    @Test
    public void 댓글테이블에서_게시물Id_값으로_댓글리스트조회(){
        List<Comment> byArticle = commentRepository.findByArticleId(1L);
        for (Comment comment : byArticle) {
            System.out.println("comment.getContents() = " + comment.getContents());
        }
        Assertions.assertEquals(byArticle.get(0).getContents(), "test");
        Assertions.assertEquals(byArticle.get(1).getContents(), "test2");
    }

    @Test
    @Transactional
    public void 게시물Id_값으로_댓글List조회() throws Exception{

        Optional<Article> byId = articleRepository.findById(1L);
        assertTrue(byId.isPresent());
        Article article = byId.get();
        System.out.println("article.getContents() = " + article.getContents());
        List<Comment> commentList = article.getCommentList();
        assertEquals(2, commentList.size());

    }

    public void 멤버_생성(){

    }
}