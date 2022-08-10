package com.idea5.playwithme.comment;

import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.comment.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO
 * 엔티티 To Dto 변환
 */
@RestController
@RequestMapping("/board")
public class CommentController {

    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {this.commentService = commentService;}

    /**
     * 댓글 리스트로 반환
     * 고민 중인 점 : 댓글 작성자의 성별까지 가져온다고 하면 그건 어떤식으로 할것인지.
     */
    @GetMapping("/comment/{board_id}/{article_id}")
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable("board_id") Long board_id, @PathVariable("article_id")Long article_id){

        //article_id를 넘겨야됨
        System.out.println("board_id = " + board_id);
        System.out.println("article_id = " + article_id);
        return null;
    }

}
