package com.idea5.playwithme.together.controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.service.TogetherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/together")
@RequiredArgsConstructor
public class TogetherController {
    private final TogetherService togetherService;
    private final MemberService memberService;
    private final ArticleService articleService;

    // 동행 회원 리스트 조회
    @GetMapping("/members")
    public String memberList(@RequestParam("article_id") Long articleId, Model model, Principal principal) {
        // TODO: 로그인 여부 검사
        Member member = memberService.findMember(principal.getName());
        Article article = articleService.findById(articleId);

        List<Together> togethers = togetherService.findByArticle(article, member);
        model.addAttribute("togethers", togethers);

        return "manner_temp_member_list";
    }

    // 동행 회원 평가폼
    @GetMapping("/review/{member_id}")
    public String reviewForm(@PathVariable("member_id") long memberId, Principal principal) {
        // 로그인한 회원이 해당 동행기록에 있는지 검사
        Member member = memberService.findMember(principal.getName());
        return "review_form";
    }
}
