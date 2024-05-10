package com.study.blog.business.admin.service;

import com.study.blog.business.admin.dto.AdminListDto;
import com.study.blog.business.admin.AdminService;
import com.study.blog.presentation.controller.request.CreateAdminRequest;
import com.study.blog.presentation.controller.request.SearchAdminRequest;
import com.study.blog.presentation.controller.request.UpdateAdminRequest;
import com.study.blog.business.user.User;
import com.study.blog.business.user.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/test-users-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("관리자 생성 서비스 검증")
    void registerAdmin_success() {
        // given
        String createEmail = "생성 테스트 이메일@email.com";
        String createPassword = "생성 테스트 비밀번호";
        String createAdminName = "생성 테스트 이름";
        CreateAdminRequest request = new CreateAdminRequest(createEmail, createPassword, createAdminName);

        // when
        Long createdAdminId = adminService.registerAdmin(request);

        // then
        User verifyAdmin = userRepository.findById(createdAdminId).get();

        assertThat(verifyAdmin).isNotNull();
        assertThat(verifyAdmin.getEmail()).isEqualTo(request.getEmail());
        assertThat(passwordEncoder.matches(request.getPassword(), verifyAdmin.getPassword())).isTrue();
        assertThat(verifyAdmin.getName()).isEqualTo(request.getAdminName());
    }

    @Test
    @DisplayName("관리자 검색 서비스, 검색 항목 일치 확인")
    void searchAdminList_success() {
        // given
        String searchEmail = "테스트";
        String searchAdminName = "테스트";
        boolean searchStatus = true;

        Pageable pageable = PageRequest.of(0, 10);
        SearchAdminRequest request = new SearchAdminRequest(searchEmail, searchAdminName, searchStatus);

        // when
        Page<AdminListDto> searchAdminList = adminService.searchAdminList(request.toData(), pageable);

        // then
        List<AdminListDto> verifyAdminResponseList = searchAdminList.getContent();

        AssertionsForClassTypes.assertThat(verifyAdminResponseList.size()).isNotZero();
        AssertionsForClassTypes.assertThat(verifyAdminResponseList.stream().anyMatch(user -> user.getEmail().contains(request.getSearchEmail()))).isTrue();
        AssertionsForClassTypes.assertThat(verifyAdminResponseList.stream().anyMatch(user -> user.getName().contains(request.getSearchName()))).isTrue();
        AssertionsForClassTypes.assertThat(verifyAdminResponseList.stream().anyMatch(AdminListDto::isStatus)).isEqualTo(request.getSearchStatus());
    }

    @Test
    @DisplayName("관리자 업데이트 서비스, 매개변수 값 일치 확인")
    void updateAdmin_success() {
        // given
        Long requestAdminId = 1L;
        String updatePassword = "변경 후 비밀번호";
        String updateName = "변경 후 이름";

        UpdateAdminRequest request = new UpdateAdminRequest(updatePassword, updateName);

        // when
        adminService.updateAdmin(requestAdminId, request);

        // then
        User verifyAdmin = userRepository.findById(requestAdminId).get();
        assertThat(verifyAdmin).isNotNull();
        assertThat(passwordEncoder.matches(request.getPassword(), verifyAdmin.getPassword())).isTrue();
        assertThat(verifyAdmin.getName()).isEqualTo(request.getAdminName());
    }

    @Test
    @DisplayName("관리자 상태 변경 서비스, 변경 여부 확인")
    void switchCommentStatus_success() {
        // given
        Long requestAdminId = 1L;
        boolean beforeStatus = userRepository.findById(requestAdminId).get().isStatus();

        // when
        adminService.switchAdminStatus(requestAdminId);

        // then
        boolean afterStatus = userRepository.findById(requestAdminId).get().isStatus();

        assertThat(afterStatus).isNotEqualTo(beforeStatus);
    }
}