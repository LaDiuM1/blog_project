package com.study.blog.infrastructure.database;

import com.study.blog.presentation.controller.request.SearchAdminRequest;
import com.study.blog.presentation.controller.response.AdminListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<AdminListResponse> searchAdminList(SearchAdminRequest request, Pageable pageable);
}
