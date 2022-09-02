package com.idea5.playwithme.article.Controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.dto.ArticleUpdateForm;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.service.BoardService;
import com.idea5.playwithme.comment.CommentService;
import com.idea5.playwithme.comment.domain.CommentCreateForm;
import com.idea5.playwithme.comment.domain.CommentDto;
import com.idea5.playwithme.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final BoardService boardService;

    // 게시글 작성폼
    @GetMapping("/write/{board_id}")
    public String createForm(Model model, @PathVariable("board_id") Long boardId, ArticleCreateForm articleCreateForm) {
        Board board = boardService.findById(boardId);
        model.addAttribute("eventName", board.getEvent().getName());

        return "article_create_form";
    }

    // 게시글 작성
    @PostMapping("/write/{board_id}")
    public String create(@PathVariable("board_id") Long boardId, @Valid ArticleCreateForm articleCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article_create_form";
        }
        // TODO: member session 처리
        Long articleId = articleService.create(boardId, articleCreateForm);
        Article article = articleService.findById(articleId);

        return "redirect:/board/%d/%d".formatted(boardId, article.getId());
    }

    // 게시글 리스트 조회
    @GetMapping("/{board_id}")
    public String getList(Model model, @PathVariable("board_id") Long boardId, @RequestParam(value = "page", defaultValue = "0") int page) {
        Board board = boardService.findById(boardId);
        Page<Article> paging = articleService.getList(boardId, page);
        model.addAttribute("paging", paging);
        model.addAttribute("eventName", board.getEvent().getName());

        return "board";
    }

    // 게시글 상세 조회
    // TODO: board_id url에 꼭 넣어야 하는가
    @GetMapping("/{board_id}/{article_id}")
    public String getDetails(Model model, @PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        Article article = articleService.getDetails(boardId, articleId);
            List<CommentDto> findCommenets = commentService.findByArticleId(articleId);
        articleService.updateViews(article);

        Event event = article.getBoard().getEvent();
        model.addAttribute("commentList", findCommenets);
        model.addAttribute("createForm", new CommentCreateForm());
        model.addAttribute("board_id", boardId);
        model.addAttribute("article", article);
        model.addAttribute("event", event);

        return "article_detail";
    }

    // 게시글 수정폼
    @GetMapping("/modify/{board_id}/{article_id}")
    public String modifyForm(Model model, @PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, ArticleUpdateForm articleUpdateForm) {
        Article article = articleService.findById(articleId);
        // 기존 값 넣기
        articleUpdateForm.setTitle(article.getTitle());
        articleUpdateForm.setContents(article.getContents());
        articleUpdateForm.setGender(article.getGender());
        // 연령대
        String[] ages = article.getAgeRange().split("~");
        articleUpdateForm.setMinAge(ages[0]);
        articleUpdateForm.setMaxAge(ages[1]);
        articleUpdateForm.setMaxRecruitNum(Integer.toString(article.getMaxRecruitNum()));

        Board board = boardService.findById(boardId);
        model.addAttribute("eventName", board.getEvent().getName());

        return "article_update_form";

    }


    // 게시글 수정
    @PostMapping("/modify/{board_id}/{article_id}")
    public String modify(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @Valid ArticleUpdateForm articleUpdateForm, BindingResult bindingResult) {
        articleService.update(articleId, articleUpdateForm);

        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }

    // 게시글 모집 상태 완료로 변경
    @GetMapping("/complete/{board_id}/{article_id}")
    @ResponseBody
    public void updateStatus(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        articleService.updateStatus(articleId);
        // TODO: 게시글 리스트 페이지 redirect
    }

    // 게시글 삭제
    @GetMapping("/delete/{board_id}/{article_id}")
    public String delete(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        articleService.delete(articleId);
        // TODO: 게시글 리스트 페이지로 리다이렉트
        return "test";
    }
}
