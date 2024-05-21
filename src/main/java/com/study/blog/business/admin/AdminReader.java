package com.study.blog.business.admin;

import com.study.blog.business.admin.data.AdminSearchData;
import com.study.blog.business.admin.dto.AdminListDto;
import com.study.blog.business.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminReader {
    private final UserRepository userRepository;

    public Page<AdminListDto> searchAdminList(AdminSearchData searchData, Pageable pageable) {
        return userRepository.searchAdminList(searchData, pageable);
    }
}
