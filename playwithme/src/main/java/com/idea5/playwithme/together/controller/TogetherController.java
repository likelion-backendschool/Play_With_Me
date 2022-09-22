package com.idea5.playwithme.together.controller;


import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.comment.service.CommentService;
import com.idea5.playwithme.event.dto.EventDto;
import com.idea5.playwithme.member.domain.Member;

import com.idea5.playwithme.member.dto.MemberRecruitDto;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.review.service.ReviewService;

import com.idea5.playwithme.together.domain.TogetherInfoDto;

import com.idea5.playwithme.mypage.service.TimelineService;

import com.idea5.playwithme.together.dto.TogetherForm;
import com.idea5.playwithme.together.service.TogetherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/together")
public class TogetherController {
    /**
     * Todo
     * 쿼리 성능 향상 V
     * 예외 처리 V
     * 이벤트랑 외래키 걸기 -> [ 이벤트 : 동행 ] V
     *                     [  1   :   N  ]
     * 동행 취소, 비활성화 기간 -> 이벤트 날짜 반영
     */

    private final MemberService memberService;

    private final TogetherService togetherService;

    private final ReviewService reviewService;

    private final ArticleService articleService;


    private final TimelineService timelineService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/manage")
    public String showManage(Principal principal, Model model) {
        String name = principal.getName();

        if (name == null || name.isEmpty()) {
            log.info("The name does not exist.......");
        }
        Member member = memberService.findMember(name);
        System.out.println("member.getId() = " + member.getId());
        List<TogetherInfoDto> togetherInfos = togetherService.findTogetherListByMemberId(member.getId());
        model.addAttribute("togetherInfos", togetherInfos);

        return "together_manage";
    }



    @GetMapping("/recruit/{board_id}/{article_id}/{member_id}")
    public String recruit(@PathVariable("board_id") Long board_id, @PathVariable("article_id") Long articleId, @PathVariable("member_id") Long memberId, Model model) {
        List<MemberRecruitDto> recruitMember = memberService.findRecruitMember(articleId, memberId);
        model.addAttribute("recruitMember", recruitMember);
        model.addAttribute("togetherForm", new TogetherForm());

        return "recruit_confirm_form";
    }

    @PostMapping("/recruit/{board_id}/{article_id}/{member_id}")
    public String form(@ModelAttribute("togetherForm") TogetherForm togetherForm, @PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @PathVariable("member_id") Long memberId) {

        Article article = articleService.findById(articleId);
        //이미 모집완료가 된 상태
        if(!article.getRecruitStatus()){
            log.warn("이미 모집완료가 된 게시판 입니다.");
            return "redirect:/board/%d/%d".formatted(boardId, articleId);
        }

        List<Long> ids = togetherForm.getIds(); // 작성자가 선택한 댓글 작성자들
        ids.add(memberId); // 작성자 아이디



        List<MemberRecruitDto> recruitMember = memberService.findRecruitMember(articleId, memberId);

        togetherService.saveTogetherAndReview(articleId, ids);
        articleService.updateStatus(articleId);


        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }

    @GetMapping("delete/{together_id}")
    public String doDelete(@PathVariable("together_id") Long togetherId, Principal principal){

        String name = principal.getName();

        if (name == null || name.isEmpty()) {
            log.info("The name does not exist.......");
        }
        Long memberId = memberService.findMember(name).getId();

        togetherService.doDelete(togetherId, memberId);
        return "redirect:/together/manage";

    }



}
