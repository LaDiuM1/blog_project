package com.study.blog.domain.admin.post.controller;

import com.study.blog.domain.admin.post.request.CreatePostRequest;
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

    @PostMapping
    public ResponseEntity<Void> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {


        return ResponseEntity.ok().build();
    }

}
