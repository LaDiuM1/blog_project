package com.study.blog.business.admin;

import com.study.blog.business.user.User;
import com.study.blog.business.user.data.Role;
import com.study.blog.business.user.repository.UserRepository;
import com.study.blog.presentation.controller.request.AdminCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminCreator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerAdmin(AdminCreateRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return userRepository.save(
                new User(request.getEmail(), encodedPassword, request.getAdminName(), Role.ADMIN)
        ).getId();
    }
}
