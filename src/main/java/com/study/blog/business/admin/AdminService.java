package com.study.blog.business.admin;

import com.study.blog.business.admin.data.SearchAdminData;
import com.study.blog.business.admin.dto.AdminListDto;
import com.study.blog.presentation.controller.request.CreateAdminRequest;
import com.study.blog.presentation.controller.request.UpdateAdminRequest;
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

    public Long registerAdmin(CreateAdminRequest createAdminRequest) {
        return adminCreator.registerAdmin(createAdminRequest);
    }

    public Page<AdminListDto> searchAdminList(SearchAdminData searchAdminData, Pageable pageable) {
        return adminReader.searchAdminList(searchAdminData, pageable);
    }

    public void updateAdmin(Long adminId, UpdateAdminRequest updateAdminRequest) {
        updateAdmin.updateAdmin(adminId, updateAdminRequest);
    }

    public void switchAdminStatus(Long adminId) {
        updateAdmin.switchAdmin(adminId);
    }
}
