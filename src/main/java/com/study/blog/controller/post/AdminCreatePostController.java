package com.study.blog.controller.post;

import com.study.blog.controller.post.request.CreatePostRequest;
import com.study.blog.service.post.PostService;
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

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {

        postService.createPost(createPostRequest);

        return ResponseEntity.ok().build();
    }

}
