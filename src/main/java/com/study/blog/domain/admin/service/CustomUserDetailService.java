package com.study.blog.domain.admin.service;

import com.study.blog.domain.admin.repository.Admin;
import com.study.blog.domain.admin.repository.AdminRepository;
import com.study.blog.domain.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = new UserDetails();
        return adminRepository.findAdminByEmail(username).orElseThrow(() -> new RuntimeException("관리자를 찾을 수 없습니다."));

    }
}
