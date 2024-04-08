package com.study.blog.domain.comment.repository;

import com.study.blog.domain.comment.request.CommentListRequest;
import com.study.blog.domain.comment.response.CommentListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<CommentListResponse> searchCommentList(CommentListRequest request, Pageable pageable);
}
