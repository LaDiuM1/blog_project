package com.study.blog.controller.post;

import com.study.blog.controller.post.request.UpdatePostRequest;
import com.study.blog.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/post/update")
@RequiredArgsConstructor
public class AdminUpdatePostController {

    private final PostService postService;


    @PutMapping()
    public ResponseEntity<Void> updatePost(@Valid @RequestBody UpdatePostRequest updatePostRequest) {

        postService.updatePost(updatePostRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/status")
    public ResponseEntity<Void> updatePostStatus(@Param("id") Long id) {

        postService.updatePostStatus(id);

        return ResponseEntity.ok().build();
    }

}
