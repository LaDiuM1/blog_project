package com.study.blog.business.admin;

import com.study.blog.business.admin.data.AdminCreateData;
import com.study.blog.business.user.User;
import com.study.blog.business.user.data.Role;
import com.study.blog.business.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminCreator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerAdmin(AdminCreateData createData) {
        String encodedPassword = passwordEncoder.encode(createData.getPassword());

        return userRepository.save(
                new User(createData.getEmail(), encodedPassword, createData.getAdminName(), Role.ADMIN)
        ).getId();
    }
}
