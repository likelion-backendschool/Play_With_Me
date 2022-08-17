package com.idea5.playwithme.article.Controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.dto.ArticleDto;
import com.idea5.playwithme.article.dto.ArticleRequestDto;
import com.idea5.playwithme.article.dto.ArticleResponseDto;
import com.idea5.playwithme.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<ArticleResponseDto> create(@PathVariable("board_id") Long boardId, @Valid ArticleCreateForm articleCreateForm, BindingResult bindingResult) {
        // TODO : 입력되지 않은 데이터 처리
        if (bindingResult.hasErrors()) {
            System.out.println("오류");
        }
        // TODO: member session 처리
        Long articleId = articleService.create(boardId, articleCreateForm);
        Article article = articleService.findById(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(ArticleResponseDto.toDto(article));
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
    @PostMapping("/modify/{board_id}/{article_id}")
    public void update(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @RequestBody ArticleRequestDto articleRequestDto) {
        articleService.update(articleId, articleRequestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{board_id}/{article_id}")
    public void delete(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        articleService.delete(articleId);
    }
}
