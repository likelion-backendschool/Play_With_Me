package com.idea5.playwithme.article.repository;

import com.idea5.playwithme.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByBoard_Id(Long boardId, Pageable pageable);

    @Query("select distinct a from Review as r inner join Article a on a.id = r.article.id where r.score = 0 and r.reviewer.id = ?1")
    List<Article> findReviewArticles(Long reviewerId);
}
