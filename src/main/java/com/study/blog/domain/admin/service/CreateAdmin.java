package com.study.blog.domain.admin.service;

import com.study.blog.domain.admin.repository.Admin;
import com.study.blog.domain.admin.repository.AdminRepository;
import com.study.blog.domain.admin.request.AdminLoginRequest;
import com.study.blog.domain.admin.request.CreateAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class CreateAdmin {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerAdmin(CreateAdminRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return adminRepository.save(
                new Admin(request.getEmail(), encodedPassword, request.getAdminName())
        ).getId();
    }
}
