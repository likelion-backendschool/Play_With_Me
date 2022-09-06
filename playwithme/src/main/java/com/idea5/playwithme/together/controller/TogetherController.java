package com.idea5.playwithme.together.controller;

import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/together")
public class TogetherController {
    /**
     * Todo
     * 게시글 작성자와
     * 댓글 리스트들을 중복없이 데이터 뽑기.
     */

    @Autowired
    CommentService commentService;

    @GetMapping("/recruit/{article_id}/{member_id}")
    public String recruit(@PathVariable("article_id") Long articleId, @PathVariable("member_id") Long memberId, Model model){
        System.out.println("articleId = " + articleId);
        System.out.println("memberId = " + memberId);

        return null;
    }



}
