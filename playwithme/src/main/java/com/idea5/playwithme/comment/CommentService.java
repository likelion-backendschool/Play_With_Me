package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.ArticleRepository;
import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.comment.domain.CommentDto;
import com.idea5.playwithme.comment.domain.CommentRequestDto;
import com.idea5.playwithme.member.MemberRepository;
import com.idea5.playwithme.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;


    /**
     * 댓글 리스트 조회
     */
    public List<CommentDto> findByArticleId(Long id){
        List<Comment> byArticleId = commentRepository.findByArticleId(id);
        List<CommentDto> dtoList = new ArrayList<>();

        for (Comment i : byArticleId) {
            dtoList.add(i.toCommentDto());
        }
        return dtoList;
    }

    @Transactional
    public Long commentSave(Long articleId, CommentRequestDto dto) {
        Article article = articleRepository.findById(articleId).orElse(null);
        dto.setArticle(article);
        dto.setMember(null);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }

    /**
     * 세션 기능 완료된 후에는 이 메서드를 사용.
     */
    public Long commentSave(Long memberId, Long articleId, CommentRequestDto dto) {
        Article article = articleRepository.findById(articleId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);
        dto.setArticle(article);
        dto.setMember(member);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();

    }

    @Transactional
    public Long commentUpdate(Long id, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id="+id));
        comment.update(dto.getContents());
        return comment.getId();
    }

    @Transactional
    public CommentDto findComment(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        return comment.toCommentDto();
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        commentRepository.delete(comment);
    }
}
