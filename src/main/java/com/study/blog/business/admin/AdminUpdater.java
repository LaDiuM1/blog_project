package com.study.blog.business.admin;

import com.study.blog.business.admin.data.AdminUpdateData;
import com.study.blog.business.admin.exception.AdminAccessDeniedException;
import com.study.blog.business.admin.exception.AdminNotFoundException;
import com.study.blog.business.user.User;
import com.study.blog.business.user.data.Role;
import com.study.blog.business.user.repository.UserRepository;
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
            throw new AdminNotFoundException();
        }else if(optionalUser.get().getRole() != Role.ADMIN){
            throw new AdminAccessDeniedException();
        }

        return optionalUser.get();
    }

    public void updateAdmin(Long adminId, AdminUpdateData adminUpdateData) {
        User admin = checkExistingAdmin(adminId);

        String encodedPassword = passwordEncoder.encode(adminUpdateData.getPassword());

        admin.updatePasswordAndName(encodedPassword, adminUpdateData.getAdminName());
    }

    public void switchAdmin(Long adminId) {
        User admin = checkExistingAdmin(adminId);
        admin.switchStatus();

    }
}
