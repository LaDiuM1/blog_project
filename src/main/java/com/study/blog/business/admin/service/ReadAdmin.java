package com.study.blog.business.admin.service;

import com.study.blog.presentation.controller.request.SearchAdminRequest;
import com.study.blog.presentation.controller.response.AdminListResponse;
import com.study.blog.business.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadAdmin {
    private final UserRepository userRepository;

    public Page<AdminListResponse> searchAdminList(SearchAdminRequest request, Pageable pageable) {
        return userRepository.searchAdminList(request, pageable);
    }
}
