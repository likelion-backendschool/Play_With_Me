package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.ArticleRepository;
import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.member.MemberRepository;
import com.idea5.playwithme.member.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        Article article1 = new Article();
        articleRepository.save(article1);
        

        Comment comment = new Comment();
        comment.setContents("test");
        comment.setSecret_status(true);
        comment.setArticle(article1);

        Comment comment2 = new Comment();
        comment2.setContents("test2");
        comment2.setSecret_status(true);
        comment2.setArticle(article1);


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
    public void 게시물Id_값으로_댓글List조회(){
        Article byId = articleRepository.getById(1L);
        List<Comment> commentList = byId.getCommentList();
        for (Comment comment : commentList) {
            System.out.println("comment.getContents() = " + comment.getContents());
        }
    }
}