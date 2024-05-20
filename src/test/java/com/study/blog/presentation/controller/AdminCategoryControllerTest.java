package com.study.blog.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.blog.business.category.CategoryService;
import com.study.blog.presentation.controller.request.CreateCategoryRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminCategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 생성 요청 컨트롤러 검증")
    void createCategory_validRequest_success() throws Exception {

        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest(
                "테스트 이름",
                "테스트 설명");

        String jsonRequest = objectMapper.writeValueAsString(createCategoryRequest);

        mockMvc.perform(post("/api/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("카테고리 생성 요청 컨트롤러 검증, 이름 100글자 초과 -> valid 메시지 응답")
    void createCategory_invalidRequest_fail() throws Exception {
        String nameLongerThan100Chars = "a".repeat(101);

        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest(
                nameLongerThan100Chars,
                "테스트 설명");

        String jsonRequest = objectMapper.writeValueAsString(createCategoryRequest);

        mockMvc.perform(post("/api/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("name = null, description = '테스트' : 필수 파라미터 null 카테고리 생성 검증")
    void createCategory_nameIsNullRequest_fail() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("description", "테스트");

        String jsonRequest = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(post("/api/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("카테고리 업데이트, 정상 파라미터 -> 성공 필요")
    void updateCategory_validRequest_success() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "테스트");
        requestBody.put("description", "테스트");

        String jsonRequest = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(put("/api/admin/categories/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 업데이트 비정상 파라미터 검증 -> badRequest")
    void updateCategory_invalidRequest_fail() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("invalidParam", "테스트");
        requestBody.put("description", "테스트");

        String jsonRequest = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(put("/api/admin/categories/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("카테고리 삭제 검증")
    void deleteCategory_validRequest_success() throws Exception {
        mockMvc.perform(delete("/api/admin/categories/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }



}