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



@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminCommentController.class)
class AdminCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    public void getCommentList() throws Exception {
        CommentListResponse commentListResponse = new CommentListResponse(
                1L, // id
                null, // parentCommentId (부모 댓글이 없는 경우 null)
                "테스트 댓글 내용", // commentContent
                "작성자 이름", // commentAuthorName
                "작성자@email.com", // commentAuthorEmail
                true, // status (댓글 상태, true or false)
                1L, // postId (댓글이 달린 게시글의 ID)
                "게시글 제목", // postTitle
                null // parentCommentContent (부모 댓글 내용이 없는 경우 null)
        );

        PageImpl<CommentListResponse> page = new PageImpl<>(Collections.singletonList(commentListResponse));

        given(commentService.getCommentList(any(CommentListRequest.class), any(PageRequest.class))).willReturn(page);

        mockMvc.perform(get("/admin/comment")
                        .param("searchKeyword", "test")
                        .param("searchStatus", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCommentStatus() throws Exception {
        mockMvc.perform(put("/admin/comment/update/status")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}