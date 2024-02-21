package com.study.blog.infrastructure.persistence.repository.post;

import com.study.blog.service.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PostRepositoryCustom {
    Post findByIdOrThrow(Long id);

    Page<PostListResponse> getPostAndCommentCountList(Set<Long> searchCategoryIds, String searchKeyword, Boolean searchStatus, Pageable pageable);
}
