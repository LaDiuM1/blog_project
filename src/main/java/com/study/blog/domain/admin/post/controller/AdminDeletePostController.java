package com.study.blog.domain.admin.post.controller;

import com.study.blog.domain.admin.post.service.AdminPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/post/delete")
@RequiredArgsConstructor
public class AdminDeletePostController {

    AdminPostService adminPostService;

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@Param("id") Long id) {

        adminPostService.deletePost(id);

        return ResponseEntity.ok().build();
    }
}
