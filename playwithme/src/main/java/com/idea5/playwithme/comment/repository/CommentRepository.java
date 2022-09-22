package com.idea5.playwithme.comment.repository;


import com.idea5.playwithme.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// @Repostiory 생략 가능
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleId(Long id);

}
