package com.study.blog.business.admin;

import com.study.blog.business.user.Role;
import com.study.blog.business.user.UserRepository;
import com.study.blog.presentation.controller.request.CreateAdminRequest;
import com.study.blog.business.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminCreator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerAdmin(CreateAdminRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return userRepository.save(
                new User(request.getEmail(), encodedPassword, request.getAdminName(), Role.ADMIN)
        ).getId();
    }
}
