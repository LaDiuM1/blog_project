package com.study.blog.domain.comment;

import com.study.blog.domain.comment.repository.Comment;
import com.study.blog.domain.comment.repository.CommentRepository;
import com.study.blog.domain.comment.response.CommentListResponse;
import com.study.blog.domain.comment.request.CommentListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentQuery commentQuery;
    private final CommentUpdater commentUpdater;

    public Page<CommentListResponse> searchCommentList(CommentListRequest request, Pageable pageable){
        return commentQuery.searchCommentList(request, pageable);
    }

    public void switchCommentStatus(Long id){
        commentUpdater.switchCommentStatus(id);
    }

}
