package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.ArticleRepository;
import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.comment.domain.CommentDto;
import com.idea5.playwithme.comment.domain.CommentRequestDto;
import com.idea5.playwithme.member.MemberRepository;
import com.idea5.playwithme.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
    }


    public List<Comment> findById(Long id){
        return commentRepository.findByArticleId(id);
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
    public void findComment(Long id) {
        System.out.println("id = " + id);
        Comment comment = commentRepository.findById(id).orElse(null);
        System.out.println("comment.getContents() = " + comment.getContents());
    }
}
