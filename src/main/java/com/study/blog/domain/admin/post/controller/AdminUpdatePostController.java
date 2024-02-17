package com.study.blog.domain.admin.post.controller;

import com.study.blog.domain.admin.post.response.PostResponse;
import com.study.blog.domain.admin.post.service.AdminPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/post/update")
@RequiredArgsConstructor
public class AdminUpdatePostController {

    private final AdminPostService adminPostService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long id) {

        PostResponse post = adminPostService.getPost(id);

        return ResponseEntity.ok(post);
    }

}
