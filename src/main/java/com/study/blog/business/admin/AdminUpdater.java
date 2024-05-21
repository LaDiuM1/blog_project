package com.study.blog.business.admin;

import com.study.blog.business.user.User;
import com.study.blog.business.user.data.Role;
import com.study.blog.business.user.repository.UserRepository;
import com.study.blog.presentation.controller.request.AdminUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class AdminUpdater {

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

    public void updateAdmin(Long adminId, AdminUpdateRequest request) {
        User admin = checkExistingAdmin(adminId);

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        admin.updatePasswordAndName(encodedPassword, request.getAdminName());
    }

    public void switchAdmin(Long adminId) {
        User admin = checkExistingAdmin(adminId);
        admin.switchStatus();

    }
}
