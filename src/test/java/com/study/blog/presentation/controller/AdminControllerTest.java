package com.study.blog.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.blog.presentation.controller.request.CreateAdminRequest;
import com.study.blog.presentation.controller.request.UpdateAdminRequest;
import com.study.blog.business.admin.AdminService;
import com.study.blog.presentation.controller.AdminController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("관리자 검색 컨트롤러, 정상 파라미터 -> isOk")
    void searchPostList_validParam_success() throws Exception {
        mockMvc.perform(get("/api/admins")
                .param("page", "1")
                .param("size", "10")
                .param("searchEmail","test@email")
                .param("searchName","testName")
                .param("searchStatus","true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("관리자 검색 컨트롤러, 비정상 파라미터 -> isBadRequest")
    void searchPostList_invalidParam_isBadRequest() throws Exception {
        mockMvc.perform(get("/api/admins")
                .param("page", "1")
                .param("size", "10")
                .param("searchEmail","test@email")
                .param("searchName","testName")
                .param("searchStatus","not Boolean")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("관리자 등록 컨트롤러, 정상 파라미터 -> isCreated")
    void registerAdmin_validParam_success() throws Exception {
        CreateAdminRequest createAdminRequest = new CreateAdminRequest("test@email", "testName", "testPassword");

        String jsonData = mapper.writeValueAsString(createAdminRequest);

        mockMvc.perform(post("/api/admins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("관리자 등록 컨트롤러, @Valid 메시지 검증")
    void registerAdmin_verifyParam_success() throws Exception {
        CreateAdminRequest createAdminRequest = new CreateAdminRequest("test", "12345", null);

        var violations = validator.validate(createAdminRequest);

        assertThat(violations).anyMatch(v -> v.getMessage().contains("이메일 형식이 아닙니다."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("비밀번호는 최소 6자리 이상이어야 합니다."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("관리자명을 입력해 주세요."));
    }

    @Test
    @DisplayName("관리자 업데이트 컨트롤러, 정상 파라미터 -> isOk")
    void updateAdmin_validParam_success() throws Exception {
        long updateAdminId = 1L;
        UpdateAdminRequest updateAdminRequest = new UpdateAdminRequest("123456", "testName");

        String jsonData = mapper.writeValueAsString(updateAdminRequest);

        mockMvc.perform(put("/api/admins/" + updateAdminId)
        .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("관리자 업데이트 컨트롤러, @Valid 메시지 검증")
    void updateAdmin_verifyParam_success() throws Exception {
        UpdateAdminRequest updateAdminRequest = new UpdateAdminRequest("12345", null);

        var violations = validator.validate(updateAdminRequest);

        assertThat(violations).anyMatch(v -> v.getMessage().contains("비밀번호는 최소 6자리 이상이어야 합니다."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("관리자명을 입력해 주세요."));
    }

    @Test
    @DisplayName("관리자 상태 변경 컨트롤러, 정상 파라미터 -> isOk")
    void switchAdminStatus_validParam_success() throws Exception {
        long updateAdminId = 1L;

        mockMvc.perform(put("/api/admins/status/" + updateAdminId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}