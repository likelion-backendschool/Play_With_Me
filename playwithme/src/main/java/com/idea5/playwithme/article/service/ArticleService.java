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

    // 게시글 내용 수정
    public void update(Long articleId, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(articleId)));
        // TODO: 수정될  수 있는 값(모집 상태 바꾸는 요청은 따로?)
        article.setTitle(articleRequestDto.getTitle());
        article.setContents(articleRequestDto.getContents());
        article.setMaxRecruitNum(articleRequestDto.getMaxRecruitNum());
        article.setUpdatedAt(LocalDateTime.now());
        article.setGender(articleRequestDto.getGender());
        article.setAgeRange(articleRequestDto.getAgeRange());

        articleRepository.save(article);
    }
}
