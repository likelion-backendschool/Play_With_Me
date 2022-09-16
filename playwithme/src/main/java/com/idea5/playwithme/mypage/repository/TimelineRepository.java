package com.idea5.playwithme.mypage.repository;

import com.idea5.playwithme.mypage.domain.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    Optional<Timeline> findById(Long id);

}
