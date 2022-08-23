package com.idea5.playwithme.article.Controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.dto.ArticleUpdateForm;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.event.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 작성폼
    @GetMapping("/write/{board_id}")
    public String createForm(@PathVariable("board_id") Long boardId, ArticleCreateForm articleCreateForm) {
        return "article_create_form";
    }

    // 게시글 작성
    @PostMapping("/write/{board_id}")
    public String create(@PathVariable("board_id") Long boardId, @Valid ArticleCreateForm articleCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors());
            return "article_create_form";
        }

        // TODO : 입력되지 않은 데이터 처리
        if (bindingResult.hasErrors()) {
            System.out.println("오류");
        }
        // TODO: member session 처리
        Long articleId = articleService.create(boardId, articleCreateForm);
        Article article = articleService.findById(articleId);

//        return ResponseEntity.status(HttpStatus.OK).body(ArticleResponseDto.toDto(article));
        return "redirect:/board/%d/%d".formatted(boardId, article.getId());
    }

    // 게시글 전체 조회

    // 게시글 상세 조회
    // TODO: board_id url에 꼭 넣어야 하는가
    @GetMapping("/{board_id}/{article_id}")
    public String getDetails(Model model, @PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        Article article = articleService.getDetails(boardId, articleId);
        Event event = article.getBoard().getEvent();
        model.addAttribute("article", article);
        model.addAttribute("event", event);
//        System.out.println(event.getName());
//        System.out.println(event.getLocation());
//        return ResponseEntity.status(HttpStatus.OK).body(article);
//        return ResponseEntity.status(HttpStatus.OK).body(ArticleDto.toDto(article));
        return "article_detail";
    }

    // 게시글 수정폼
    @GetMapping("/modify/{board_id}/{article_id}")
    public String modifyForm(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, ArticleUpdateForm articleUpdateForm) {
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

        return "article_update_form";
    }


    // 게시글 수정
    @PostMapping("/modify/{board_id}/{article_id}")
    @ResponseBody
    public void modify(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @Valid ArticleUpdateForm articleUpdateForm, BindingResult bindingResult) {
        articleService.update(articleId, articleUpdateForm);
    }

    // 게시글 모집 상태 완료로 변경
    @GetMapping("/complete/{board_id}/{article_id}")
    @ResponseBody
    public void updateStatus(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        articleService.updateStatus(articleId);
        // TODO: 게시글 리스트 페이지 redirect
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{board_id}/{article_id}")
    @ResponseBody
    public void delete(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        articleService.delete(articleId);
    }
}
