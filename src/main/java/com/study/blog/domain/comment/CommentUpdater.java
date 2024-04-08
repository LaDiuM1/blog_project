package com.study.blog.domain.comment;

import com.study.blog.domain.comment.repository.Comment;
import com.study.blog.domain.comment.repository.CommentRepository;
import com.study.blog.domain.comment.request.CommentListRequest;
import com.study.blog.domain.comment.response.CommentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class CommentUpdater {
    private final CommentRepository commentRepository;

    @Transactional
    public void switchCommentStatus(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        comment.switchStatus();
    }

}
