package com.study.blog.api;

import com.study.blog.domain.tag.service.TagService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminTagController.class)
class AdminTagControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Test
    @DisplayName("태그 검색, 정상 파라미터 -> isOk")
    public void searchTag() throws Exception {
        mockMvc.perform(get("/admin/tag/search")
                .param("keyword", "태그1"))
                .andExpect(status().isOk());
    }

}