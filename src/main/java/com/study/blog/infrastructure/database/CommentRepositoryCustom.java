package com.study.blog.infrastructure.database;

import com.study.blog.presentation.controller.request.CommentListRequest;
import com.study.blog.presentation.controller.response.CommentListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<CommentListResponse> searchCommentList(CommentListRequest request, Pageable pageable);
}
