package com.idea5.playwithme.together.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TogetherService {
    private final TogetherRepository togetherRepository;

    public List<Together> findByArticle(Article article, Member member) {

        return togetherRepository.findByArticle_IdAndMember_IdNotLike(article.getId(), member.getId());
    }
}
