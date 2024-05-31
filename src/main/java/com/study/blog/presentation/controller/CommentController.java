package com.study.blog.presentation.controller;

import com.study.blog.business.comment.CommentService;
import com.study.blog.business.comment.dto.CommentListDto;
import com.study.blog.presentation.controller.request.CommentSearchRequest;
import com.study.blog.presentation.controller.response.SuccessfulResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<CommentListDto>> searchCommentList(@ModelAttribute CommentSearchRequest request,
                                                                  Pageable pageable) {
        Page<CommentListDto> postList = commentService.searchCommentList(request.toData(), pageable);

        return SuccessfulResponse.response(postList);
    }

    @PutMapping("/status/{commentId}")
    public ResponseEntity<Void> switchCommentStatus(@PathVariable("commentId") Long commentId) {
        commentService.switchCommentStatus(commentId);

        return ResponseEntity.ok().build();
    }

}
