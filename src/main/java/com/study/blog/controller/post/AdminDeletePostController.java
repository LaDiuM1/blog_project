package com.study.blog.controller.post;

import com.study.blog.service.post.PostService;
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

    PostService postService;

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@Param("id") Long id) {

        postService.deletePost(id);

        return ResponseEntity.ok().build();
    }
}
