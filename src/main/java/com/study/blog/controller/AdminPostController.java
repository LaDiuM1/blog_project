package com.study.blog.controller;

import com.study.blog.infrastructure.persistence.repository.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.repository.post.response.PostResponse;
import com.study.blog.service.post.PostService;
import com.study.blog.service.post.request.CreatePostRequest;
import com.study.blog.service.post.request.PostListRequest;
import com.study.blog.service.post.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/post")
@RequiredArgsConstructor
public class AdminPostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long id) {

        PostResponse post = postService.getPost(id);

        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<Page<PostListResponse>> searchPostList(
                                                              @RequestParam(required = false) Long searchCategoryId,
                                                              @RequestParam(required = false) String searchKeyword,
                                                              @RequestParam(required = false) Boolean searchStatus,
                                                              Pageable pageable) {
        PostListRequest postListRequest = new PostListRequest();
        postListRequest.setSearchCategoryId(searchCategoryId);
        postListRequest.setSearchKeyword(searchKeyword);
        postListRequest.setSearchStatus(searchStatus);


        Page<PostListResponse> postList = postService.searchPostList(postListRequest, pageable);

        return ResponseEntity.ok(postList);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {

        postService.createPost(createPostRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updatePost(@Valid @RequestBody UpdatePostRequest updatePostRequest) {

        postService.updatePost(updatePostRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/status")
    public ResponseEntity<Void> updatePostStatus(@RequestParam("id") Long id) {

        postService.updatePostStatus(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePost(@RequestParam("id") Long id) {

        postService.deletePost(id);

        return ResponseEntity.ok().build();
    }

}
