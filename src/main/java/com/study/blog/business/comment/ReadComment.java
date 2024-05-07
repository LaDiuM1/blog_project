package com.study.blog.business.comment;

import com.study.blog.business.comment.CommentRepository;
import com.study.blog.presentation.controller.request.CommentListRequest;
import com.study.blog.presentation.controller.response.CommentListResponse;
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
