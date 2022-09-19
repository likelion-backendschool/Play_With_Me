package com.idea5.playwithme.member.repository;

import com.idea5.playwithme.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    @Query("select distinct m.nickname, m.id FROM Member m join m.commentList c where c.article.id = ?1 and c.member.id != ?2")
    // 이정도 쿼리는 spring data jpa로 충분히 가능하다.
    List<Object[]> findRecruitMembers(Long articleId, Long memberId);

    @Query("select m.nickname, m.id, m.username From Member m join m.togetherList t where t.article.id = ?1 and t.member.id != ?2")
    List<Object[]> findMembersByArticleIdAndMemberIdNot(Long articleId, Long memberId);

    /**
     *
     * findDistinctByCommentList_articleIdAndMemberIdNot(articleId, memberId)
     */


}
