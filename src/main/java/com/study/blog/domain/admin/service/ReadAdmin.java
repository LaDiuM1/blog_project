package com.study.blog.domain.admin.service;

import com.study.blog.domain.admin.request.SearchAdminRequest;
import com.study.blog.domain.admin.response.AdminListResponse;
import com.study.blog.domain.common.UserRepository;
import com.study.blog.domain.post.response.PostListResponse;
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
