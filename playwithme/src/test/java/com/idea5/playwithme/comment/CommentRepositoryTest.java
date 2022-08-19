package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
    @BeforeEach
    public void beforeEach(){
        commentRepository.deleteAll();
        articleRepository.deleteAll();

        Article article1 = new Article();
        article1.setContents("articleContents");
        articleRepository.save(article1);

        Article findArticle = articleRepository.findById(1L).orElse(null);
        System.out.println("findArticle.getContents() = " + findArticle.getContents());


        Comment comment = new Comment();
        comment.setContents("test");
        comment.setSecretStatus(true);
        comment.setArticle(findArticle);

        Comment comment2 = new Comment();
        comment2.setContents("test2");
        comment2.setSecretStatus(true);
        comment2.setArticle(findArticle);

        


        commentRepository.save(comment);
        commentRepository.save(comment2);
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
}