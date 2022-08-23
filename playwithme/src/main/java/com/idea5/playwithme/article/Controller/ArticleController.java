package com.idea5.playwithme.article.Controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.*;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.comment.CommentService;
import com.idea5.playwithme.comment.domain.CommentCreateForm;
import com.idea5.playwithme.comment.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @Autowired
    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
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
    public String getDetails(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, Model model) {
        Article article = articleService.getDetails(boardId, articleId);
        List<CommentDto> findCommenets = commentService.findByArticleId(articleId);
        model.addAttribute("article", article);
        model.addAttribute("commentList", findCommenets);
        model.addAttribute("createForm", new CommentCreateForm());
        for (CommentDto i : findCommenets) {
            System.out.println("i.getMember() = " + i.getMember());
            System.out.println("i.getContents() = " + i.getContents());
            System.out.println("i.getParent() = " + i.getParent());
        }

        return "article_detail";
    }

    // 게시글 수정
    @PostMapping("/modify/{board_id}/{article_id}")
    public void update(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @Valid ArticleUpdateForm articleUpdateForm, BindingResult bindingResult) {
        articleService.update(articleId, articleUpdateForm);
    }

    // 게시글 모집 상태 완료로 변경
    @GetMapping("/complete/{board_id}/{article_id}")
    public void updateStatus(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        articleService.updateStatus(articleId);
        // TODO: 게시글 리스트 페이지 redirect
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{board_id}/{article_id}")
    public void delete(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        articleService.delete(articleId);
    }
}
