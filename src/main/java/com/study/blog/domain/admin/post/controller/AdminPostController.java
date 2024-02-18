package com.study.blog.domain.admin.post.controller;

import com.study.blog.domain.admin.post.request.PostListRequest;
import com.study.blog.domain.admin.post.response.PostListResponse;
import com.study.blog.domain.admin.post.response.PostResponse;
import com.study.blog.domain.admin.post.service.AdminPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/post")
@RequiredArgsConstructor
public class AdminPostController {

    private final AdminPostService adminPostService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long id) {

        PostResponse post = adminPostService.getPost(id);

        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Page<PostListResponse>> getPostList(@RequestBody PostListRequest postListRequest, Pageable pageable) {

        Page<PostListResponse> postList = adminPostService.getPostList(postListRequest, pageable);

        return ResponseEntity.ok(postList);
    }


}
