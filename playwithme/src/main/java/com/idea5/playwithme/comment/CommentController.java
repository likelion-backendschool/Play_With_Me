package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.comment.domain.CommentDto;
import com.idea5.playwithme.comment.domain.CommentCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO
 * 비밀 상태 : 수정
 *
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/board/comment")
public class CommentController {
    private final CommentService commentService;
    /**
     * 게시글에 달려있던 댓글 리스트 조회
     * Todo
     * 댓글 뷰에 닉네임 추가. 마이페이지 이동.
     *
     */
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
    //localhost:
    @PostMapping("/write/{board_id}/{article_id}")
    public String writeComment(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @Valid CommentCreateForm createForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            System.out.println("바인딩 에러 발생");
            /**
             * Todo
             * 에러 처리
             */
        }
        System.out.println("createForm.isSecretStatus() = " + createForm.isSecretStatus());
        Long commentId = commentService.commentSave(articleId, createForm); // 로그인 세션 추가되면 변경해야 됨.
        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }

    /**
     * 대댓글 작성
     */
    @PostMapping("/write/{board_id}/{article_id}/{comment_id}")
    public String reWriteComment(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @PathVariable("comment_id") Long commentId, CommentCreateForm createForm)
    {
        System.out.println("--------------------대댓글 작성--------------------");
        commentService.commentReSave(articleId, createForm, commentId); // 로그인 세션 추가되면 변경해야 됨.

        return "redirect:/board/%d/%d".formatted(boardId, articleId);
    }


    /**
     * 수정
     */
    @PostMapping("/modify/{comment_id}")
    public ResponseEntity<CommentDto> modifyComment(@PathVariable("comment_id") Long id, CommentCreateForm createForm) {

        System.out.println("id = " + id);
        Long commentId = commentService.commentUpdate(id, createForm);// 로그인 세션 추가되면 변경해야 됨.
        return null;
    }

    /**
     * 삭제
     */

    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity delete(@PathVariable("comment_id") Long id) {
        commentService.delete(id);
        return null;
    }


    @Autowired
    ArticleService articleService;
    @GetMapping("/article_save")
    public void testSave(){
//        articleService.test();
    }

}
