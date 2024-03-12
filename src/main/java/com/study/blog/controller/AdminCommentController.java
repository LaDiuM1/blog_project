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
                                                              @RequestParam(required = false) Set<Long> testIds, // 테스트 용도
                                                              Pageable pageable) {
        CommentListRequest commentListRequest = new CommentListRequest();
        commentListRequest.setSearchKeyword(searchKeyword);
        commentListRequest.setSearchStatus(searchStatus);

        System.out.println("testIds = " + testIds);
        // 사용자 요청 발생 시 ConversionService가 요청을 분석, 쿼리스트링이 있을 경우 구문 분석을 진행하며
        // 쉼표 형태 발견 시 Converter와 Formatter 구현체가 쉼표를 배열 자료구조 형태로 변환하는 작업을 진행

        Page<CommentListResponse> postList = commentService.getCommentList(commentListRequest, pageable);

        return ResponseEntity.ok(postList);
    }

}
