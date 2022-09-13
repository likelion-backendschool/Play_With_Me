package com.idea5.playwithme.together.controller;

import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.comment.service.CommentService;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.MemberRecruitDto;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.review.domain.Review;
import com.idea5.playwithme.review.service.ReviewService;
import com.idea5.playwithme.together.dto.TogetherForm;
import com.idea5.playwithme.together.service.TogetherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/together")
public class TogetherController {
    /**
     * Todo
     * 쿼리 성능 향상
     * 예외 처리
     */


    @Autowired
    MemberService memberService;

    @Autowired
    TogetherService togetherService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/recruit/{board_id}/{article_id}/{member_id}")
    public String recruit(@PathVariable("board_id") Long board_id, @PathVariable("article_id") Long articleId, @PathVariable("member_id") Long memberId, Model model) {
        List<MemberRecruitDto> recruitMember = memberService.findRecruitMember(articleId, memberId);
        model.addAttribute("recruitMember", recruitMember);
        model.addAttribute("togetherForm", new TogetherForm());
        return "recruit_confirm_form";
    }

    @PostMapping("/recruit/{board_id}/{article_id}/{member_id}")
    public String form(@ModelAttribute("togetherForm") TogetherForm togetherForm, @PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @PathVariable("member_id") Long memberId) {

        List<Long> ids = togetherForm.getIds(); // 작성자가 선택한 댓글 작성자들
        ids.add(memberId); // 작성자 아이디
        System.out.println("ids.size() = " + ids.size());

        togetherService.saveTogetherAndReview(articleId, ids);

        /**
         * 2명 (A, B) -- 2개
         * A -> B
         * B -> A
         *
         * 3명 (A, B, C) -- 6개
         * A -> B
         * A -> C
         * B -> A
         * B - >C
         * C -> A
         * C- > B
         *
         * 4명(A, B, C, D) - 12개
         * A -> B
         * A -> C
         * A - >D
         *
         * 5명(A, B, C, D, E) - 20개 - 4 x 5
         * A -> B
         * A -> C
         * A -> D
         * A -> E
         *
         *
         */

        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }
}
