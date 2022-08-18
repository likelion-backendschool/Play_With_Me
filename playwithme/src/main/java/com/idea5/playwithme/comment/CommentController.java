package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.ArticleService;
import com.idea5.playwithme.comment.domain.CommentDto;
import com.idea5.playwithme.comment.domain.CommentCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO
 * 엔티티 To Dto 변환 V
 * delete 추가
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/board/comment")

public class CommentController {

    private final CommentService commentService;
    /**
     * 게시글에 달려있던 댓글 리스트 조회
     */
    @GetMapping("/{board_id}/{article_id}")
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
     * 질문)
     * 댓글은 게시글과 유저랑만 관계가 있음.
     * -> board_id가 과연 필요할까?
     * 유저 아이디 (fk)는 세션으로 하면 될거 같음
     * -> @LoginUser
     *
     * 현재 로그인한 회원이 누구인지.
     * -> HttpSession session으로 하자.
     *
     */
    //localhost:
    @PostMapping("/write/{board_id}/{article_id}")
    public ResponseEntity<CommentDto> writeComment(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @Valid @RequestBody CommentCreateForm createForm)
    {
        Long commentId = commentService.commentSave(articleId, createForm); // 로그인 세션 추가되면 변경해야 됨.
        return null;
    }

    @PostMapping("/modify/{comment_id}")
    public ResponseEntity<CommentDto> modifyComment(@PathVariable("comment_id") Long id, @RequestBody CommentCreateForm createForm)
    {
        System.out.println("id = " + id);
        Long commentId = commentService.commentUpdate(id, createForm);// 로그인 세션 추가되면 변경해야 됨.
        return null;
    }

    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity delete(@PathVariable("comment_id") Long id) {
        commentService.delete(id);
        return null;
    }


    @Autowired
    ArticleService articleService;
    @GetMapping("/article_save")
    public void testSave(){
        articleService.test();
    }

}
