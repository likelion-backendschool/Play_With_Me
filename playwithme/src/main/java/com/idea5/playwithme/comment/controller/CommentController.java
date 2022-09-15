package com.idea5.playwithme.comment.controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.comment.service.CommentService;
import com.idea5.playwithme.comment.dto.CommentDto;
import com.idea5.playwithme.comment.dto.CommentCreateForm;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * TODO
 * 예외처리
 *
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/board/comment")
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    private final MemberService memberService;

    @GetMapping("/{article_id}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("board_id") Long boardId, @PathVariable("article_id")Long articleId){

        List<CommentDto> byArticleId = commentService.findByArticleId(articleId);
        return ResponseEntity.ok(byArticleId);
    }

    /**
     * 댓글 조회기능
     */

    @GetMapping("/{comment_id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("comment_id") Long id){
        CommentDto commentDto = commentService.findComment(id);
        return ResponseEntity.ok(commentDto);
    }


    /**
     * 작성
     */
    @PreAuthorize("isAuthenticated()")
    //localhost:
    @PostMapping("/write/{board_id}/{article_id}")
    public String writeComment(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @Valid CommentCreateForm createForm, BindingResult bindingResult, Principal principal)
    {
        System.out.println("작성 메서드 실행");

        if(bindingResult.hasErrors()){
            System.out.println("바인딩 에러 발생");
            return "redirect:/board/%d/%d".formatted(boardId, articleId);
        }
        String name = principal.getName();
        Member findMember = memberService.findMember(name);
        commentService.commentSave(articleId, createForm, findMember);
        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }

    /**
     * 대댓글 작성
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write/{board_id}/{article_id}/{comment_id}")
    public String reWriteComment(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @PathVariable("comment_id") Long commentId, @Valid CommentCreateForm createForm, BindingResult bindingResult, Principal principal)
    {
        if(bindingResult.hasErrors()){
            System.out.println("바인딩 에러 발생");
            return "redirect:/board/%d/%d".formatted(boardId, articleId);
        }
        String name = principal.getName();
        Member findMember = memberService.findMember(name);
        commentService.commentReSave(articleId, createForm, commentId,findMember);
        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }


    /**
     * 수정
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{board_id}/{article_id}/{comment_id}")
    public String modifyComment(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @PathVariable("comment_id") Long id, @Valid CommentCreateForm createForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println("바인딩 에러 발생");
            return "redirect:/board/%d/%d".formatted(boardId, articleId);
        }

        commentService.commentUpdate(id, createForm);
        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }

    /**
     * 삭제
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{board_id}/{article_id}/{comment_id}")
    public String delete(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @PathVariable("comment_id") Long id) {
        commentService.delete(id);
        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }



}
