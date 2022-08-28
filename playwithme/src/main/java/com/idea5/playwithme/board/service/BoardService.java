package com.idea5.playwithme.board.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow();
    }
}
