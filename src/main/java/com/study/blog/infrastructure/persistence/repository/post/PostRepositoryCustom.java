package com.study.blog.infrastructure.persistence.repository.post;

import com.study.blog.domain.admin.post.response.PostListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PostRepositoryCustom {
    Page<PostListResponse> getPostList(Set<Long> searchCategoryIds, String searchKeyword, Boolean searchStatus, Pageable pageable);
}
