package com.study.blog.infrastructure.database;

import com.study.blog.presentation.controller.request.SearchAdminRequest;
import com.study.blog.business.admin.AdminListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<AdminListDto> searchAdminList(SearchAdminRequest request, Pageable pageable);
}
