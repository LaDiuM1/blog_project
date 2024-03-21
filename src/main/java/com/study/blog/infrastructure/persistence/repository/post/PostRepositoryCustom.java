package com.study.blog.infrastructure.persistence.repository.post;

import com.study.blog.infrastructure.persistence.repository.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Post findByIdOrThrow(Long id);

    Page<PostListResponse> searchPostAndCommentCountList(Long searchCategoryId, String searchKeyword, Boolean searchStatus, Pageable pageable);
}
