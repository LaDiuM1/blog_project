package com.study.blog.domain.admin.service;

import com.study.blog.domain.admin.repository.AdminRepository;
import com.study.blog.domain.admin.request.AdminLoginRequest;
import com.study.blog.domain.admin.request.CreateAdminRequest;
import com.study.blog.domain.admin.request.UpdateAdminRequest;
import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.post.response.PostResponse;
import com.study.blog.domain.post.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AuthenticationManager authenticationManager;
    private final CreateAdmin createAdmin;
    private final UpdateAdmin updateAdmin;

    public Object login(AdminLoginRequest request){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

//        UserDetails principal = (UserDetails)
        return null;
    }

    public Long registerAdmin(CreateAdminRequest createAdminRequest) {
        return createAdmin.registerAdmin(createAdminRequest);
    }

    public void updateAdmin(Long adminId, UpdateAdminRequest updateAdminRequest) {
        updateAdmin.updateAdmin(adminId, updateAdminRequest);
    }
}
