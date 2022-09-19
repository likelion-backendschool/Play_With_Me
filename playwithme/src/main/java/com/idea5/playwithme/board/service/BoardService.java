package com.idea5.playwithme.board.service;

import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow();
    }

    public Board findByEvent_Id(Long id) {
        return boardRepository.findByEvent_Id(id).orElse(null);
    }
}
