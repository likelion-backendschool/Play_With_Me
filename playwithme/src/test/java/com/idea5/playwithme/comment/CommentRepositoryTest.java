package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.domain.repository.BoardRepository;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.comment.repository.CommentRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
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
//    @BeforeEach
    public void beforeEach(){
        commentRepository.deleteAll();
        articleRepository.deleteAll();
        memberRepository.deleteAll();
        boardRepository.deleteAll();
        Member member1 = new Member();
        member1.setName("memberA");

        Member member2 = new Member();
        member2.setName("memberB");

        Member member3 = new Member();
        member3.setName("memberC");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);


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
                .title("20대 남성분 중에 흠뻑쇼 가실분")
                .contents("댓글 남겨주시면 오픈채팅방 드리곘습니다.")
//                .maxRecruitNum(5)
                .gender("Female")
//                .ageRange("10~20")
                .build();
        Article article = ArticleCreateForm.toEntity(articleCreateForm);
        article.setMember(member1);
        // when
        articleRepository.save(article);


        Comment comment = new Comment();
        comment.setContents("test 댓글 작성.");
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
        comment2.setUpdatedAt(LocalDateTime.now());
        comment2.setArticle(article);
        comment2.setMember(member2);
        commentRepository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setContents("참여하고 싶습니다.");
        comment3.setSecretStatus(false);
        comment3.setCreatedAt(LocalDateTime.now());
        comment3.setUpdatedAt(LocalDateTime.now());
        comment3.setArticle(article);
        comment3.setMember(member2);
        commentRepository.save(comment3);

        Comment comment4 = new Comment();
        comment4.setContents("넵!! 환영합니다!!");
        comment4.setSecretStatus(false);
        comment4.setCreatedAt(LocalDateTime.now());
        comment4.setUpdatedAt(LocalDateTime.now());
        comment4.setArticle(article);
        comment4.setMember(member1);
        comment4.confirmParent(comment3);
        commentRepository.save(comment4);

        Comment comment5 = new Comment();
        comment5.setContents("저도 참여하고 싶어요");
        comment5.setSecretStatus(false);
        comment5.setCreatedAt(LocalDateTime.now());
        comment5.setUpdatedAt(LocalDateTime.now());
        comment5.setArticle(article);
        comment5.setMember(member2);
        comment5.confirmParent(comment3);
        commentRepository.save(comment5);


        Comment comment6 = new Comment();
        comment6.setContents("오픈 채팅방 링크 가능하신가요?");
        comment6.setSecretStatus(false);
        comment6.setCreatedAt(LocalDateTime.now());
        comment6.setUpdatedAt(LocalDateTime.now());
        comment6.setArticle(article);
        comment6.setMember(member3);
        commentRepository.save(comment6);

        Comment comment7 = new Comment();
        comment7.setContents("http://open~");
        comment7.setSecretStatus(true);
        comment7.setCreatedAt(LocalDateTime.now());
        comment7.setUpdatedAt(LocalDateTime.now());
        comment7.setArticle(article);
        comment7.setMember(member1);
        comment7.confirmParent(comment6);
        commentRepository.save(comment7);


    }

    @Test
    public void 테스트(){
        System.out.println("test");
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