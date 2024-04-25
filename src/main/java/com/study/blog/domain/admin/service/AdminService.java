package com.study.blog.domain.admin.service;

import com.study.blog.domain.admin.request.CreateAdminRequest;
import com.study.blog.domain.admin.request.SearchAdminRequest;
import com.study.blog.domain.admin.request.UpdateAdminRequest;
import com.study.blog.domain.admin.response.AdminListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CreateAdmin createAdmin;
    private final UpdateAdmin updateAdmin;
    private final ReadAdmin readAdmin;

    public Long registerAdmin(CreateAdminRequest createAdminRequest) {
        return createAdmin.registerAdmin(createAdminRequest);
    }

    public Page<AdminListResponse> searchAdminList(SearchAdminRequest request, Pageable pageable) {
        return readAdmin.searchAdminList(request, pageable);
    }

    public void updateAdmin(Long adminId, UpdateAdminRequest updateAdminRequest) {
        updateAdmin.updateAdmin(adminId, updateAdminRequest);
    }

    public void switchAdminStatus(Long adminId) {
        updateAdmin.switchAdmin(adminId);
    }
}
