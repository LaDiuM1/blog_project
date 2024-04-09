package com.study.blog.api;

import com.study.blog.domain.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCommentController.class)
class AdminCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    void getCommentList_invalidStatusParam_fail() throws Exception {
        mockMvc.perform(get("/admin/comment")
                        .param("searchKeyword", "test")
                        .param("searchStatus", "NotBoolean")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCommentList_invalidParams_fail() throws Exception {
        mockMvc.perform(get("/admin/comment")
                        .param("searchKeyword", "test")
                        .param("searchStatus", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCommentList_noParams_success() throws Exception {
        mockMvc.perform(get("/admin/comment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void updateCommentStatus_idParam_success() throws Exception {
        mockMvc.perform(put("/admin/comment/update/status")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateCommentStatus_noParam_fail() throws Exception {
        mockMvc.perform(put("/admin/comment/update/status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}