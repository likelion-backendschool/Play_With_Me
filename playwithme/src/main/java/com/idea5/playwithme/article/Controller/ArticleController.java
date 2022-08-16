package com.idea5.playwithme.article.Controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleDto;
import com.idea5.playwithme.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 작성
    @PostMapping("/write/{board_id}")
    public ResponseEntity<ArticleDto> create(@PathVariable("board_id") Long boardId, @RequestBody ArticleDto articleDto) {
        articleDto.setBoardId(boardId);
        Article article = ArticleDto.toEntity(articleDto);
        article = articleService.create(article);
        return ResponseEntity.status(HttpStatus.OK).body(ArticleDto.toDto(article));
    }

    // 게시글 전체 조회

    // 게시글 상세 조회
    // TODO: board_id url에 꼭 넣어야 하는가
    @GetMapping("/{board_id}/{article_id}")
    public ResponseEntity<ArticleDto> getDetails(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        Article article = articleService.getDetails(boardId, articleId);

        return ResponseEntity.status(HttpStatus.OK).body(ArticleDto.toDto(article));
    }

    // 게시글 수정

    // 게시글 삭제

}
