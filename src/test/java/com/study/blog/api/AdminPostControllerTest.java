package com.study.blog.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import com.study.blog.domain.post.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AdminPostController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminPostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;
    @Autowired
    ObjectMapper objectMapper;

    private Validator validator;

    @BeforeEach // 메서드 실행 전 validator 객체 초기화
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("특정 포스트 호출 검증, 정상 파라미터 -> isOk")
    void getPost_validParam_success() throws Exception {
        mockMvc.perform(get("/api/admin/posts/" + 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 리스트 검색 검증, 정상 파라미터 -> isOk")
    void searchPostList_validParam_success() throws Exception {
        mockMvc.perform(get("/api/admin/posts")
                .param("searchCategoryId", "1")
                .param("searchKeyword", "test2")
                .param("searchStatus", "true"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 리스트 검색 검증, 파라미터 없음 -> isOk")
    void searchPostList_noParams_success() throws Exception {
        mockMvc.perform(get("/api/admin/posts"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("게시글 생성 검증, null값 유효성 검증 확인")
    void createPost_nullParams_validate() throws Exception {
        CreatePostRequest request = new CreatePostRequest(
                null, null, null, new HashSet<>(List.of("")));

        var violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().contains("카테고리 id 값이 null 입니다."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("글 제목을 입력해 주세요."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("글 내용을 입력해 주세요."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("태그는 공백으로만 이루어질 수 없습니다."));
    }

    @Test
    @DisplayName("게시글 생성 검증, 유효성 범위 초과 확인")
    void createPost_outOfRange_invalid() {
        String rangeOverTitle = "a".repeat(101);
        CreatePostRequest request = new CreatePostRequest(
                0L, rangeOverTitle, "test", new HashSet<>(List.of("test")));

        var violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().contains("카테고리 id는 정수 1 이상 요청바랍니다."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("글 제목은 100글자를 넘을 수 없습니다."));
    }

    @Test
    @DisplayName("게시글 업데이트 검증, null값 유효성 검증 확인")
    void updatePost_nullParams_validate() {
        UpdatePostRequest request = new UpdatePostRequest(
                null, null, null, new HashSet<>(List.of("")));

        var violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().contains("카테고리 id 값이 null 입니다."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("글 제목을 입력해 주세요."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("글 내용을 입력해 주세요."));
        assertThat(violations).anyMatch(v -> v.getMessage().contains("태그는 공백으로만 이루어질 수 없습니다."));
    }
}