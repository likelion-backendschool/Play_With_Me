package com.idea5.playwithme.article.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticleDetail(Long boardId, Long articleId) {
        return findById(articleId);
    }
    public Article findById(Long articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }
}
