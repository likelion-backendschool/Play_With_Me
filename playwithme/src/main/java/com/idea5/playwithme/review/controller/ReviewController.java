package com.idea5.playwithme.review.controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.MemberInfoDTO;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.review.domain.Review;
import com.idea5.playwithme.review.domain.ReviewDto;
import com.idea5.playwithme.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final MemberService memberService;
    private final ArticleService articleService;

    // 리뷰 관리
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/manage")
    public String reviewList(Model model, Principal principal) {
        Member member = memberService.findMember(principal.getName());
        MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();

        // 로그인한 회원 중 아직 리뷰를 완료하지 않은 게시글
        List<Article> articleList = articleService.getReviewArticelList(member.getId());

        articleList.sort(new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                LocalDate d1 = o1.getBoard().getEvent().getDate().toLocalDate();
                LocalDate d2 = o2.getBoard().getEvent().getDate().toLocalDate();

                if(d1.isEqual(d2)) {
                    // article id 오름차순 정렬
                    return (int) (o1.getId() - o2.getId());
                }
                // event 날짜 오름차순 정렬
                return d1.compareTo(d2);
            }
        });

        // 평가날짜(오늘 날짜)가 이벤트 날짜보다 전이면 평가X
        int i = 0;
        while(true) {
            if(articleList.size() - 1 < i) {
                break;
            }
            Article article = articleList.get(i);
            LocalDate eventDate = article.getBoard().getEvent().getDate().toLocalDate();
            if(LocalDate.now().compareTo(eventDate) <= 0) {
                articleList.remove(article);
                continue;
            }
            i++;
        }

        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("articleList", articleList);
        model.addAttribute("localDate", LocalDate.now());

        return "review_list";
    }

    // 회원 매너 평가폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{article_id}")
    public String reviewForm(Model model, ReviewDto reviewDto, @PathVariable("article_id") Long articleId, Principal principal) {
        Article article = articleService.findById(articleId);
        Member member = memberService.findMember(principal.getName());

        List<Review> reviewList = reviewService.getReviewList(articleId, member.getId());
        if(reviewList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "평가 권한이 없습니다.");
        }

        // 평가날짜(오늘 날짜)가 이벤트 날짜보다 전이면 평가X
        LocalDate eventDate = article.getBoard().getEvent().getDate().toLocalDate();
        if(LocalDate.now().compareTo(eventDate) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "평가 가능한 기간이 아닙니다.");
        }
//        System.out.println("eventDate = " + eventDate);
//        System.out.println(LocalDate.now());

//        model.addAttribute("reviewList", reviewList);
        reviewDto.setReviewList(reviewList);

        return "review_form";
    }

    // 회원 매너 평가
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{article_id}")
    public String review(@PathVariable("article_id") Long articleId, ReviewDto reviewDto, BindingResult bindingResult, Principal principal) {
        List<Review> reviewList = reviewDto.getReviewList();
        for (Review r : reviewList) {
            System.out.println(r.getReviewee().getId());
            System.out.println(r.getScore());
        }

        reviewService.review(reviewDto);

        return "redirect:/review/manage";
    }
}
