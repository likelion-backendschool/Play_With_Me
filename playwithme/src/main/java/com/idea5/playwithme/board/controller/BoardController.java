package com.idea5.playwithme.board.controller;

import com.idea5.playwithme.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시판(게시글 리스트) 조회
//    @GetMapping("/{board_id}")
//    public String getList(@PathVariable("board_id") Long boardId) {
//        Board board = boardService.findById(boardId);
//        return "board";
//    }

}
