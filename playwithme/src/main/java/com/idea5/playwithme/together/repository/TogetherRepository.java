package com.idea5.playwithme.together.repository;

import com.idea5.playwithme.together.domain.Together;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TogetherRepository extends JpaRepository<Together, Long> {
    // 해당 게시글 id의 동행 목록 중에서 현재 로그인한 회원빼고 조회
    List<Together> findByArticle_IdAndMember_IdNotLike(long articleId, long memberId);
}
