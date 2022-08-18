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

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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

    public Article getDetails(Long boardId, Long articleId) {
        return findById(articleId);
    }
    public Article findById(Long articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }

    // 게시글 내용 수정
    @Transactional
    public void update(Long articleId, ArticleUpdateForm articleUpdateForm) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(articleId)));
        // TODO: 수정될  수 있는 값(모집 상태 바꾸는 요청은 따로?)
        article.setTitle(articleUpdateForm.getTitle());
        article.setContents(articleUpdateForm.getContents());
        article.setMaxRecruitNum(articleUpdateForm.getMaxRecruitNum());
        article.setGender(articleUpdateForm.getGender());
        article.setAgeRange(articleUpdateForm.getAgeRange());
        article.setUpdatedAt(LocalDateTime.now());

        articleRepository.save(article);
    }

    // 게시글 삭제
    public void delete(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(articleId)));
        articleRepository.delete(article);
    }

    public void updateStatus(Long articleId) {
        // 모집 완료(false)로 변경
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NullPointerException("%d 게시물 not found".formatted(articleId)));
        article.setRecruitStatus(false);

        articleRepository.save(article);
        // TODO: 동행 테이블에 댓글 남긴 자동으로 회원 정보 저장?..(선택 시점 확인 필요)
        // TODO: 공연 날짜 이후에 게시글 작성자가 동행 인원 확정하기 -> 확정 인원을 바탕으로 매너 평가
    }
}
