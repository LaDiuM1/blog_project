package com.study.blog.domain.admin.post.controller;

import com.study.blog.domain.admin.post.request.UpdatePostRequest;
import com.study.blog.domain.admin.post.response.PostResponse;
import com.study.blog.domain.admin.post.service.AdminPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PutMapping()
    public ResponseEntity<Void> updatePost(@Valid @RequestBody UpdatePostRequest updatePostRequest) {

        adminPostService.updatePost(updatePostRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/status")
    public ResponseEntity<Void> updatePostStatus(@Param("id") Long id) {

        adminPostService.updatePostStatus(id);

        return ResponseEntity.ok().build();
    }

}
