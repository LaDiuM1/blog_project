package com.study.blog.domain.comment.service;

import com.study.blog.domain.comment.repository.Comment;
import com.study.blog.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateComment {
    private final CommentRepository commentRepository;

    @Transactional
    public void switchCommentStatus(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        comment.switchStatus();
    }

}
