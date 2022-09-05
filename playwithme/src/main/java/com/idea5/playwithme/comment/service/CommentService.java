package com.idea5.playwithme.comment.service;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.comment.dto.CommentDto;
import com.idea5.playwithme.comment.dto.CommentCreateForm;
import com.idea5.playwithme.comment.repository.CommentRepository;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    @Transactional
//    public Long commentSave(Long articleId, CommentCreateForm dto) {
//        Article article = articleRepository.findById(articleId).orElseThrow(() ->
//                new IllegalArgumentException("%d번 게시글은 존재하지 않습니다.".formatted(articleId)));
//
//        dto.setArticle(article);
//        dto.setMember(null);
//
//        Comment comment = dto.toEntity();
//        commentRepository.save(comment);
//
//        return dto.getId();
//    }

    @Transactional
    public Long commentSave(Long articleId, CommentCreateForm dto, Member member) {
        Article article = articleRepository.findById(articleId).orElseThrow(()->
                    new IllegalArgumentException("%d번 게시글은 존재하지 않습니다.".formatted(articleId)));

        dto.setArticle(article);
        dto.setMember(member);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();

    }

    @Transactional
    public void commentReSave(Long articleId, CommentCreateForm createForm, Long parentId, Member member) {
        createForm.setMember(member);
        Comment comment = createForm.toEntity();
        comment.confirmArticle(articleRepository.findById(articleId).orElseThrow(() ->
                new IllegalArgumentException("%d번 게시글은 존재 하지 않습니다.".formatted(articleId))));

        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(() ->
                new IllegalArgumentException("%d번 부모 댓글 존재하지 않습니다.".formatted(parentId))));

        commentRepository.save(comment);

    }

    @Transactional
    public Long commentUpdate(Long id, CommentCreateForm dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id="+id));
        comment.update(dto.getContents(), dto.isSecretStatus());
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public CommentDto findComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id="+id));
        return comment.toCommentDto();
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다." + id));

        commentRepository.delete(comment);
    }
}
