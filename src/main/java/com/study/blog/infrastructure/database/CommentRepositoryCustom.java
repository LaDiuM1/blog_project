package com.study.blog.infrastructure.database;

import com.study.blog.presentation.controller.request.CommentListRequest;
import com.study.blog.business.comment.CommentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<CommentListDto> searchCommentList(CommentListRequest request, Pageable pageable);
}
