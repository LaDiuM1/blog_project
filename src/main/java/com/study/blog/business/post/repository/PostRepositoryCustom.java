package com.study.blog.business.post.repository;

import com.study.blog.business.post.Post;
import com.study.blog.business.post.dto.PostListDto;
import com.study.blog.presentation.controller.request.PostSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Post findByIdOrThrow(Long id);
    Page<PostListDto> searchPostList(PostSearchRequest request, Pageable pageable);
}
