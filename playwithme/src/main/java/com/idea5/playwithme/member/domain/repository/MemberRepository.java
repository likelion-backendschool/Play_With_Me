package com.idea5.playwithme.member.domain.repository;

import com.idea5.playwithme.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
