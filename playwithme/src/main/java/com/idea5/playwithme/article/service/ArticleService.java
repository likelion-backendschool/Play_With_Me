package com.idea5.playwithme.article.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.dto.ArticleCreateForm;
import com.idea5.playwithme.article.dto.ArticleUpdateForm;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.domain.repository.BoardRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Long boardId, ArticleCreateForm articleCreateForm) {
        Board board = boardRepository.findById(boardId).orElse(null);
        // TODO: member 처리(테스트를 위해 무조건 memberId 1로 세팅해놓음)
        Member member = memberRepository.findById(1L).orElse(null);
        
        // 변환 후 값 채우기
        Article article = ArticleCreateForm.toEntity(articleCreateForm);
        article.setBoard(board);
        article.setMember(member);
        // 게시글 저장 후 ID 반환
        Article saveArticle = articleRepository.save(article);
        System.out.println(saveArticle.getId());
        return saveArticle.getId();
    }

    // 게시글 상세조회
    public Article getDetails(Long boardId, Long articleId) {
        return findById(articleId);
    }

    @Transactional(readOnly = true)
    public Article findById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(articleId)));
//        return articleRepository.findById(articleId).orElse(null);
    }

    // 게시글 내용 수정
    @Transactional
    public void update(Long articleId, ArticleUpdateForm articleUpdateForm) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(articleId)));

        articleUpdateForm.toEntity(article, articleUpdateForm);
        articleRepository.save(article);
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(articleId)));
        articleRepository.delete(article);
    }

    // 게시글 상태 모집완료(false)로 변경
    @Transactional
    public void updateStatus(Long articleId) {
        // 모집 완료(false)로 변경
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(articleId)));
        article.setRecruitStatus(false);

        articleRepository.save(article);
    }

    // 게시글 조회수 증가
    @Transactional
    public void updateViews(Article article) {
        // TODO: 새로 쿼리를 짜야하는지, 여기서 바로 set으로 넣는게 괜찮은지
        article.setViews(article.getViews() + 1);
        articleRepository.save(article);
    }
}
