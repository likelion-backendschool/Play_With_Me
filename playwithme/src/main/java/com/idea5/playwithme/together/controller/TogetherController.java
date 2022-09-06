package com.idea5.playwithme.together.controller;

import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.comment.service.CommentService;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.MemberRecruitDto;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.together.dto.TogetherForm;
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
     * 게시글 작성자와
     * 댓글 리스트들을 중복없이 데이터 뽑기.
     */

//    @ModelAttribute("nicknames")
//    private Map<Long, String> nicknames(){
//        Map<>
//    }

    @Autowired
    MemberService memberService;

    @GetMapping("/recruit/{board_id}/{article_id}/{member_id}")
    public String recruit(@PathVariable("board_id") Long board_id, @PathVariable("article_id") Long articleId, @PathVariable("member_id") Long memberId, Model model) {
        List<MemberRecruitDto> recruitMember = memberService.findRecruitMember(articleId, memberId);
        model.addAttribute("recruitMember", recruitMember);
        model.addAttribute("togetherForm", new TogetherForm());
        return "recruit_confirm_form";
    }

    @PostMapping("/recruit/{article_id}/{member_id}")
    public String form(@ModelAttribute("togetherForm") TogetherForm togetherForm, @PathVariable("article_id") Long articleId, @PathVariable("member_id") Long memberId) {

        List<Long> ids = togetherForm.getIds(); // 작성자가 선택한 댓글 작성자들
        ids.add(memberId); // 작성자 아이디

        for (Long id : ids) {
            System.out.println("id = " + id);
        }


        return "redirect:/";
    }
}
