package com.idea5.playwithme.article.Controller;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.dto.ArticleUpdateForm;
import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.service.BoardService;
import com.idea5.playwithme.comment.service.CommentService;
import com.idea5.playwithme.comment.dto.CommentCreateForm;
import com.idea5.playwithme.comment.dto.CommentDto;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.MemberInfoDTO;
import com.idea5.playwithme.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final BoardService boardService;
    private final MemberService memberService;

    // 게시글 작성폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write/{board_id}")
    public String createForm(Model model, @PathVariable("board_id") Long boardId, ArticleCreateForm articleCreateForm, Principal principal) {
        if (principal != null && principal.getName().length()!=0) {
            Member member = memberService.findMember(principal.getName());
            MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();
            model.addAttribute("memberInfo", memberInfo);
        }

        Board board = boardService.findById(boardId);
        model.addAttribute("eventName", board.getEvent().getName());

        return "article_create_form";
    }

    // 게시글 작성
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write/{board_id}")
    public String create(@PathVariable("board_id") Long boardId, @Valid ArticleCreateForm articleCreateForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult = " + bindingResult.getErrorCount());
            return "article_create_form";
        }
        // 나이대 유효성 검사
        if(Integer.parseInt(articleCreateForm.getMinAge()) > Integer.parseInt(articleCreateForm.getMaxAge())){
            bindingResult.rejectValue("minAge", "MisMatch", "모집 나이대를 올바르게 설정해주세요.");
            return "article_create_form";
        }

        Member member = memberService.findMember(principal.getName());
        Long articleId = articleService.create(boardId, articleCreateForm, member);
        Article article = articleService.findById(articleId);

        return "redirect:/board/%d/%d".formatted(boardId, article.getId());
    }

    // 게시글 리스트 조회
    @GetMapping("/{board_id}")
    public String getList(Model model, @PathVariable("board_id") Long boardId, @RequestParam(value = "page", defaultValue = "0") int page, Principal principal) {
        if (principal != null && principal.getName().length()!=0) {
            Member member = memberService.findMember(principal.getName());
            MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();
            model.addAttribute("memberInfo", memberInfo);
        }

        Board board = boardService.findById(boardId);
        Page<Article> paging = articleService.getList(boardId, page);
        model.addAttribute("paging", paging);
        model.addAttribute("eventName", board.getEvent().getName());

        System.out.println("paging.getTotalElements() = " + paging.getTotalElements());

        return "board";
    }

    // 게시글 상세 조회
    @GetMapping("/{board_id}/{article_id}")
    public String getDetails(Model model, @PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, Principal principal) {
        if (principal != null && principal.getName().length()!=0) {
            Member member = memberService.findMember(principal.getName());
            MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();
            model.addAttribute("memberInfo", memberInfo);
        }

        Article article = articleService.getDetails(boardId, articleId);
        List<CommentDto> findCommenets = commentService.findByArticleId(articleId);
        articleService.updateViews(article);

        Event event = article.getBoard().getEvent();
        model.addAttribute("commentList", findCommenets);
        model.addAttribute("createForm", new CommentCreateForm());
        model.addAttribute("board_id", boardId);
        model.addAttribute("article", article);
        model.addAttribute("event", event);

        return "article_detail";
    }


    // 게시글 수정폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{board_id}/{article_id}")
    public String modifyForm(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId,
                             @RequestParam("returnUrl") String returnUrl,
                             Model model, ArticleUpdateForm articleUpdateForm, Principal principal) {

        // 메뉴바 회원 닉네임, 성별 내용 추가
        Member member = memberService.findMember(principal.getName());
        MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();

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

        Board board = boardService.findById(boardId);

        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("eventName", board.getEvent().getName());
        model.addAttribute("returnUrl", returnUrl);

        return "article_update_form";
    }


    // 게시글 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{board_id}/{article_id}")
    public String modify(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId,
                         @RequestParam("returnUrl") String returnUrl,
                         @Valid ArticleUpdateForm articleUpdateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article_update_form";
        }

        // 나이대 유효성 검사
        if(Integer.parseInt(articleUpdateForm.getMinAge()) > Integer.parseInt(articleUpdateForm.getMaxAge())){
            bindingResult.rejectValue("minAge", "MisMatch", "모집 나이대를 올바르게 설정해주세요.");
            return "article_update_form";
        }

        articleService.update(articleId, articleUpdateForm);

        // 게시글 관리 페이지 -> 수정 요청한 경우
        if(returnUrl.equals("board-manage")) {
            return "redirect:/board/manage";
        }
        // 게시글 상세 페이지 -> 수정 요청한 경우
        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }

    // 게시글 모집 상태 완료로 변경
    @GetMapping("/complete/{board_id}/{article_id}")
    @ResponseBody
    public void updateStatus(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        articleService.updateStatus(articleId);
        // TODO: 게시글 리스트 페이지 redirect
    }

    // 게시글 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{board_id}/{article_id}")
    public String delete(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId,
                         @RequestParam("returnUrl") String returnUrl) {
        articleService.delete(articleId);

        // 게시글 관리 페이지 -> 삭제 요청한 경우
        if(returnUrl.equals("board-manage")) {
            return "redirect:/board/manage";
        }
        // 게시글 상세 페이지 -> 삭제 요청한 경우
        return "redirect:/board/%d".formatted(boardId);
    }

    // 게시글 관리
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/manage")
    public String manage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "all") String category, @RequestParam(defaultValue = "new") String sortCode, Principal principal, Model model) {
        Member member = memberService.findMember(principal.getName());
        MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();

        Page<Article> paging = articleService.getMyList(member.getId(), page, category, sortCode);
        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("paging", paging);
        model.addAttribute("category", category);
        model.addAttribute("sortCode", sortCode);

        return "article_manage";
    }
}
