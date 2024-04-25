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
@Transactional
public class UpdateAdmin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private User checkExistingAdmin(Long adminId){
        Optional<User> optionalUser = userRepository.findById(adminId);

        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException();
        }else if(optionalUser.get().getRole() != Role.ADMIN){
            throw new EntityNotFoundException("해당 계정은 관리자가 아닙니다.");
        }

        return optionalUser.get();
    }

    public void updateAdmin(Long adminId, UpdateAdminRequest request) {
        User admin = checkExistingAdmin(adminId);

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        admin.updatePasswordAndName(encodedPassword, request.getAdminName());
    }

    public void switchAdmin(Long adminId) {
        User admin = checkExistingAdmin(adminId);
        admin.switchStatus();

    }
}
