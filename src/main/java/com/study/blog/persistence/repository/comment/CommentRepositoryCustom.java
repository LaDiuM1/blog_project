package com.study.blog.persistence.repository.comment;

import com.study.blog.persistence.repository.comment.response.CommentListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<CommentListResponse> getCommentList(String searchKeyword, Boolean searchStatus, Pageable pageable);
}
