package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.ArticleService;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.comment.domain.CommentDto;
import com.idea5.playwithme.comment.domain.CommentRequestDto;
import com.idea5.playwithme.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/board")

public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 리스트로 반환
     * 고민 중인 점 : 댓글 작성자의 성별까지 가져온다고 하면 그건 어떤식으로 할것인지.
     */
    @GetMapping("/comment/{board_id}/{article_id}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("board_id") Long boardId, @PathVariable("article_id")Long articleId){

        System.out.println("articleId = " + articleId);

        return null;
    }

    @GetMapping("/comment/{comment_id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("comment_id") Long id){
        commentService.findComment(id);
        return null;
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
    @PostMapping("/comment/write/{board_id}/{article_id}")
    public ResponseEntity<CommentDto> writeComment(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId, @Valid @RequestBody CommentRequestDto dto)
    {
        Long commentId = commentService.commentSave(articleId, dto); // 로그인 세션 추가되면 변경해야 됨.
        return null;
    }

    @PostMapping("/comment/modify/{comment_id}")
    public ResponseEntity<CommentDto> modifyComment(@PathVariable("comment_id") Long id, @RequestBody CommentRequestDto dto)
    {
        System.out.println("id = " + id);
        Long commentId = commentService.commentUpdate(id, dto);// 로그인 세션 추가되면 변경해야 됨.
        return null;
    }

    @DeleteMapping("/comment/delete/{comment_id}")
    public ResponseEntity delete(@PathVariable("comment_id") Long id) {
        commentService.delete(id);
        return null;
    }


    @Autowired
    ArticleService articleService;
    @GetMapping("/comment/article_save")
    public void testSave(){
        articleService.test();
    }

}
