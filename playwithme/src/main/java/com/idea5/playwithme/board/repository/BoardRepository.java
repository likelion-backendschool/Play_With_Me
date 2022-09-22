package com.idea5.playwithme.board.repository;

import com.idea5.playwithme.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByEvent_Id(Long id);
}
