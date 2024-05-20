package com.study.blog.presentation.controller;

import com.study.blog.business.comment.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCommentController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    void getCommentList_invalidStatusParam_fail() throws Exception {
        mockMvc.perform(get("/api/admin/comments")
                        .param("searchContent", "test")
                        .param("searchStatus", "NotBoolean")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCommentList_invalidParams_fail() throws Exception {
        mockMvc.perform(get("/api/admin/comments")
                        .param("searchContent", "test")
                        .param("searchStatus", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCommentList_noParams_success() throws Exception {
        mockMvc.perform(get("/api/admin/comments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void switchCommentStatus_idParam_success() throws Exception {
        mockMvc.perform(put("/api/admin/comments/status/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}