package com.study.blog.presentation.controller;

import com.study.blog.business.comment.dto.CommentListDto;
import com.study.blog.business.comment.CommentService;
import com.study.blog.presentation.controller.request.CommentListRequest;
import com.study.blog.presentation.controller.response.SuccessfulResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/comments")
@RequiredArgsConstructor
public class AdminCommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<CommentListDto>> searchCommentList(
                                                              @RequestParam(required = false) String searchContent,
                                                              @RequestParam(required = false) Boolean searchStatus,
                                                              Pageable pageable) {
        CommentListRequest commentListRequest = new CommentListRequest(searchContent, searchStatus);

        Page<CommentListDto> postList = commentService.searchCommentList(commentListRequest.toData(), pageable);

        return SuccessfulResponse.response(postList);
    }

    @PutMapping("/status/{commentId}")
    public ResponseEntity<Void> switchCommentStatus(@PathVariable("commentId") Long commentId) {
        commentService.switchCommentStatus(commentId);

        return ResponseEntity.ok().build();
    }

}
