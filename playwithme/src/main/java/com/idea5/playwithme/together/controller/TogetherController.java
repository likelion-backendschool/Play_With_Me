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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Member member = memberService.findMember(principal.getName());
        Article article = articleService.findById(articleId);

        List<Together> togethers = togetherService.findByArticle(article, member);
        model.addAttribute("togethers", togethers);

        return "manner_temp_member_list";
    }
}
