package com.study.blog.domain.admin.post.controller;

import com.study.blog.domain.admin.post.request.CreatePostRequest;
import com.study.blog.domain.admin.post.service.AdminPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/post/create")
@RequiredArgsConstructor
public class AdminCreatePostController {

    private final AdminPostService adminPostService;

    @PostMapping
    public ResponseEntity<Void> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {

        adminPostService.createPost(createPostRequest);

        return ResponseEntity.ok().build();
    }

}
