package com.study.blog.domain.admin.service;

import com.study.blog.domain.admin.repository.Admin;
import com.study.blog.domain.admin.repository.AdminRepository;
import com.study.blog.domain.admin.request.UpdateAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateAdmin {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void updateAdmin(Long adminId, UpdateAdminRequest request) {
        Admin updateAdmin = adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new);
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        updateAdmin.updatePasswordAndName(encodedPassword, request.getAdminName());
    }

}
