package com.study.blog.business.admin;

import com.study.blog.presentation.controller.request.CreateAdminRequest;
import com.study.blog.presentation.controller.request.SearchAdminRequest;
import com.study.blog.presentation.controller.request.UpdateAdminRequest;
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

    public Page<AdminListDto> searchAdminList(SearchAdminRequest request, Pageable pageable) {
        return readAdmin.searchAdminList(request, pageable);
    }

    public void updateAdmin(Long adminId, UpdateAdminRequest updateAdminRequest) {
        updateAdmin.updateAdmin(adminId, updateAdminRequest);
    }

    public void switchAdminStatus(Long adminId) {
        updateAdmin.switchAdmin(adminId);
    }
}
