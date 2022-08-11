package com.idea5.playwithme.article.Controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleDto;
import com.idea5.playwithme.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 작성

    // 게시글 전체 조회

    // 게시글 상세 조회
    @GetMapping("/{board_id}/{article_id}")
    public ResponseEntity<ArticleDto> getArticleDetail(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        Article article = articleService.getArticleDetail(boardId, articleId);

        return ResponseEntity.status(HttpStatus.OK).body(ArticleDto.toDto(article));
    }

    // 게시글 수정

    // 게시글 삭제

}
