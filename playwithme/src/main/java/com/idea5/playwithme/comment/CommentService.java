package com.idea5.playwithme.comment;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.article.repository.ArticleRepository;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.comment.domain.CommentDto;
import com.idea5.playwithme.comment.domain.CommentCreateForm;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.domain.repository.MemberRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        System.out.println("-------findByArticleId----------");
        for (Comment i : byArticleId) {
            System.out.println("i.getContents() = " + i.getContents());
            System.out.println("i.getParent() = " + i.getParent());
            dtoList.add(i.toCommentDto());
        }
        System.out.println("-----------------------------");
        return dtoList;
    }

    @Transactional
    public Long commentSave(Long articleId, CommentCreateForm dto) {
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
    public Long commentSave(Long memberId, Long articleId, CommentCreateForm dto) {
        Article article = articleRepository.findById(articleId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);
        dto.setArticle(article);
        dto.setMember(member);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();

    }

    @Transactional
    public void commentReSave(Long articleId, CommentCreateForm createForm, Long parentId) {
        Comment comment = createForm.toEntity();
        //Todo
        // orElseThrow 처리 해야됨.

        comment.confirmArticle(articleRepository.findById(articleId).orElseThrow());
        comment.confirmParent(commentRepository.findById(parentId).orElseThrow());

        commentRepository.save(comment);

    }

    @Transactional
    public Long commentUpdate(Long id, CommentCreateForm dto) {
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
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다." + id));

        commentRepository.delete(comment);
    }
}
