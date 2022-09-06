package com.idea5.playwithme.member.repository;

import com.idea5.playwithme.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    @Query("select distinct m FROM Member m join m.commentList c where c.article.id = ?1 and c.member.id != ?2" )
    List<Member> findRecruitMember(Long articleId, Long memberId);
}
