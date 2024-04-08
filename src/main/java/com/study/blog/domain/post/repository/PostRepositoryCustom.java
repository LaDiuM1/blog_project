package com.study.blog.domain.post.repository;

import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.response.PostListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Post findByIdOrThrow(Long id);
    Page<PostListResponse> searchPost(PostListRequest request, Pageable pageable);
}
