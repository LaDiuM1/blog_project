package com.study.blog.business.admin;

import com.study.blog.business.admin.data.AdminSearchData;
import com.study.blog.business.admin.dto.AdminListDto;
import com.study.blog.presentation.controller.request.AdminCreateRequest;
import com.study.blog.presentation.controller.request.AdminUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminCreator adminCreator;
    private final AdminUpdater updateAdmin;
    private final AdminReader adminReader;

    public Long registerAdmin(AdminCreateRequest adminCreateRequest) {
        return adminCreator.registerAdmin(adminCreateRequest);
    }

    public Page<AdminListDto> searchAdminList(AdminSearchData adminSearchData, Pageable pageable) {
        return adminReader.searchAdminList(adminSearchData, pageable);
    }

    public void updateAdmin(Long adminId, AdminUpdateRequest adminUpdateRequest) {
        updateAdmin.updateAdmin(adminId, adminUpdateRequest);
    }

    public void switchAdminStatus(Long adminId) {
        updateAdmin.switchAdmin(adminId);
    }
}
