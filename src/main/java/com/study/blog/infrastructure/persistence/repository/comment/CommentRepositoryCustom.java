package com.study.blog.infrastructure.persistence.repository.comment;

import com.study.blog.infrastructure.persistence.entity.Post;
import com.study.blog.infrastructure.persistence.repository.comment.response.CommentListResponse;
import com.study.blog.infrastructure.persistence.repository.post.response.PostListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<CommentListResponse> getCommentList(String searchKeyword, Boolean searchStatus, Pageable pageable);
}
