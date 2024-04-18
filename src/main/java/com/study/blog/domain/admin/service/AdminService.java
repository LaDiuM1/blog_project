package com.study.blog.domain.admin.service;

import com.study.blog.domain.admin.request.SearchAdminRequest;
import com.study.blog.domain.admin.response.AdminListResponse;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.user.request.LoginRequest;
import com.study.blog.domain.admin.request.CreateAdminRequest;
import com.study.blog.domain.admin.request.UpdateAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AuthenticationManager authenticationManager;
    private final CreateAdmin createAdmin;
    private final UpdateAdmin updateAdmin;
    private final ReadAdmin readAdmin;

    public Object login(LoginRequest request){
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

    public Page<AdminListResponse> searchAdminList(SearchAdminRequest request, Pageable pageable) {
        return readAdmin.searchAdminList(request, pageable);
    }
}
