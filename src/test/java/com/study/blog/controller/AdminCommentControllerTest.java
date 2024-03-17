package com.study.blog.controller;

import com.study.blog.infrastructure.persistence.repository.comment.response.CommentListResponse;
import com.study.blog.service.comment.CommentService;
import com.study.blog.service.comment.request.CommentListRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
    public void getCommentList_invalidStatusParam_fail() throws Exception {
        mockMvc.perform(get("/admin/comment")
                        .param("searchKeyword", "test")
                        .param("searchStatus", "NotBoolean")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCommentList_invalidParams_fail() throws Exception {
        mockMvc.perform(get("/admin/comment")
                        .param("searchKeyword", "test")
                        .param("searchStatus", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCommentList_noParams_success() throws Exception {
        mockMvc.perform(get("/admin/comment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void updateCommentStatus_idParam_success() throws Exception {
        mockMvc.perform(put("/admin/comment/update/status")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCommentStatus_noParam_fail() throws Exception {
        mockMvc.perform(put("/admin/comment/update/status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}