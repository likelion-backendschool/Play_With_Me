package com.idea5.playwithme.article.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleRequestDto;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.domain.repository.BoardRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Long boardId, ArticleRequestDto articleRequestDto) {
        // board, member 넣기
        Board board = boardRepository.findById(boardId).orElse(null);
        articleRequestDto.setBoard(board);
        Member member = memberRepository.findById(1L).orElse(null);
        articleRequestDto.setMember(member);
        // 변환 후 값 채우기
        Article article = ArticleRequestDto.toEntity(articleRequestDto);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        // 게시글 저장 후 ID 반환
        articleRepository.save(article);

        return article.getId();
    }

    public Article getDetails(Long boardId, Long articleId) {
        return findById(articleId);
    }
    public Article findById(Long articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }

//    // 게시글 내용 수정
//    public void modify(Article article) {
//        Long id = article.getId();
//        Article findArticle = articleRepository.findById(id)
//                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(id)));
//        // TODO: 수정될  수 있는 값
//        findArticle.setTitle(article.getTitle());
//        findArticle.setContents(article.getContents());
//        findArticle.setMaxRecruitNum(article.getMaxRecruitNum());
//        findArticle.setUpdatedAt(LocalDateTime.now());
//        findArticle.setGender(article.getGender());
//        findArticle.setAgeRange(article.getAgeRange());
//
//        articleRepository.save(findArticle);
//    }
}
