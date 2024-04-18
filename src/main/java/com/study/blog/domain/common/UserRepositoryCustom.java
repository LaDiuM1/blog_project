package com.study.blog.domain.common;

import com.study.blog.domain.admin.request.SearchAdminRequest;
import com.study.blog.domain.admin.response.AdminListResponse;
import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.request.SearchPostRequest;
import com.study.blog.domain.post.response.PostListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<AdminListResponse> searchAdminList(SearchAdminRequest request, Pageable pageable);
}
