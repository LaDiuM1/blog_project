package com.study.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.blog.service.category.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCategoryController.class)
class AdminCategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("name = '테스트', description = '테스트' : 정상 파라미터 카테고리 생성 검증")
    void createCategory_validRequest_success() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "테스트");
        requestBody.put("description", "테스트");

        String jsonRequest = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(post("/admin/category/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("name 100글자 초과' : 카테고리 생성 파라미터 유효성 검증")
    void createCategory_invalidRequest_fail() throws Exception {
        String nameLongerThan100Chars = "a".repeat(101);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", nameLongerThan100Chars);
        requestBody.put("description", "테스트");

        String jsonRequest = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(post("/admin/category/create")
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

        mockMvc.perform(post("/admin/category/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());


    }

    @Test
    @DisplayName("카테고리 업데이트 정상 파라미터 검증")
    void updateCategory_validRequest_success() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("name", "테스트");
        requestBody.put("description", "테스트");

        String jsonRequest = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(put("/admin/category/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 업데이트 비정상 파라미터 검증")
    void updateCategory_invalidRequest_fail() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", null);
        requestBody.put("name", "테스트");
        requestBody.put("description", "테스트");

        String jsonRequest = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(put("/admin/category/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("카테고리 삭제 정상 파라미터 검증")
    void deleteCategory_validRequest_success() throws Exception {
        mockMvc.perform(delete("/admin/category/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 삭제 비정상 파라미터 검증")
    void deleteCategory_invalidRequest_fail() throws Exception {
        mockMvc.perform(delete("/admin/category/delete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }




}