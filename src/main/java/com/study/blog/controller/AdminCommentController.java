package com.study.blog.controller;

import com.study.blog.infrastructure.persistence.repository.comment.response.CommentListResponse;
import com.study.blog.infrastructure.persistence.repository.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.repository.post.response.PostResponse;
import com.study.blog.service.comment.CommentService;
import com.study.blog.service.comment.request.CommentListRequest;
import com.study.blog.service.post.PostService;
import com.study.blog.service.post.request.CreatePostRequest;
import com.study.blog.service.post.request.PostListRequest;
import com.study.blog.service.post.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/admin/comment")
@RequiredArgsConstructor
public class AdminCommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<CommentListResponse>> getCommentList(
                                                              @RequestParam(required = false) String searchKeyword,
                                                              @RequestParam(required = false) Boolean searchStatus,
                                                              Pageable pageable) {
        CommentListRequest commentListRequest = new CommentListRequest();
        commentListRequest.setSearchKeyword(searchKeyword);
        commentListRequest.setSearchStatus(searchStatus);

        Page<CommentListResponse> postList = commentService.getCommentList(commentListRequest, pageable);

        return ResponseEntity.ok(postList);
    }

    @PutMapping("/update/status")
    public ResponseEntity<Void> updateCommentStatus(@RequestParam(value = "id") Long id) {

        commentService.updateCommentStatus(id);

        return ResponseEntity.ok().build();
    }

}
