package com.study.blog.domain.admin.service;

import com.study.blog.domain.common.Role;
import com.study.blog.domain.common.User;
import com.study.blog.domain.common.UserRepository;
import com.study.blog.domain.admin.request.UpdateAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateAdmin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void updateAdmin(Long adminId, UpdateAdminRequest request) {
        Optional<User> optionalUpdateUser = userRepository.findById(adminId);

        if(optionalUpdateUser.isEmpty()){
            throw new EntityNotFoundException();
        }else if(optionalUpdateUser.get().getRole() != Role.ADMIN){
            throw new EntityNotFoundException("해당 계정은 관리자가 아닙니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        optionalUpdateUser.get().updatePasswordAndName(encodedPassword, request.getAdminName());
    }

}
