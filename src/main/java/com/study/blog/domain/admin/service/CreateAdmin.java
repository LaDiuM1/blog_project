package com.study.blog.domain.admin.service;

import com.study.blog.domain.common.Role;
import com.study.blog.domain.common.UserRepository;
import com.study.blog.domain.admin.request.CreateAdminRequest;
import com.study.blog.domain.common.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAdmin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerAdmin(CreateAdminRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return userRepository.save(
                new User(request.getEmail(), encodedPassword, request.getAdminName(), Role.ADMIN)
        ).getId();
    }
}
