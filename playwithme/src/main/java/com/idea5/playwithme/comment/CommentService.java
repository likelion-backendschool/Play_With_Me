package com.idea5.playwithme.comment;

import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.comment.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {this.commentRepository = commentRepository;}

    public List<Comment> findById(Long id){
        return commentRepository.findByArticleId(id);
    }
}
