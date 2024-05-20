package com.study.blog.business.user.repository;

import com.study.blog.business.admin.data.SearchAdminData;
import com.study.blog.business.admin.dto.AdminListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<AdminListDto> searchAdminList(SearchAdminData searchAdminData, Pageable pageable);
}
