package com.study.blog.domain.comment.service;

import com.study.blog.domain.comment.repository.CommentRepository;
import com.study.blog.domain.comment.request.CommentListRequest;
import com.study.blog.domain.comment.response.CommentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadComment {
    private final CommentRepository commentRepository;

    public Page<CommentListResponse> searchCommentList(CommentListRequest request, Pageable pageable){
        return commentRepository.searchCommentList(request, pageable);
    }

}
