package com.idea5.playwithme.review.controller;

import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.review.domain.Review;
import com.idea5.playwithme.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final MemberService memberService;

    // 회원 만족도 평가폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String reviewForm(Model model, @RequestParam("article_id") Long articleId, Principal principal) {
        Member member = memberService.findMember(principal.getName());

        List<Review> reviewList = reviewService.getReviewList(articleId, member.getId());
        if(reviewList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "평가 권한이 없습니다.");
        }

        model.addAttribute("reviewList", reviewList);

        return "review_form";
    }
}
