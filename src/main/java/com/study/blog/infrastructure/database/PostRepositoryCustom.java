package com.study.blog.infrastructure.database;

import com.study.blog.business.post.Post;
import com.study.blog.presentation.controller.request.SearchPostRequest;
import com.study.blog.business.post.PostListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Post findByIdOrThrow(Long id);
    Page<PostListDto> searchPostList(SearchPostRequest request, Pageable pageable);
}
