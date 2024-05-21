package com.study.blog.business.admin;

import com.study.blog.business.admin.data.AdminCreateData;
import com.study.blog.business.admin.data.AdminSearchData;
import com.study.blog.business.admin.data.AdminUpdateData;
import com.study.blog.business.admin.dto.AdminListDto;
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

    public Long registerAdmin(AdminCreateData createData) {
        return adminCreator.registerAdmin(createData);
    }

    public Page<AdminListDto> searchAdminList(AdminSearchData searchData, Pageable pageable) {
        return adminReader.searchAdminList(searchData, pageable);
    }

    public void updateAdmin(Long adminId, AdminUpdateData updateData) {
        updateAdmin.updateAdmin(adminId, updateData);
    }

    public void switchAdminStatus(Long adminId) {
        updateAdmin.switchAdmin(adminId);
    }
}
