package com.idea5.playwithme.together.repository;

import com.idea5.playwithme.together.domain.Together;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TogetherRepository extends JpaRepository<Together, Long> {
    Together findByArticle_IdAndMember_Id(Long articleId, Long memberId);
}
