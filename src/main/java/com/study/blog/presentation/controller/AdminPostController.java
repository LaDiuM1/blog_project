package com.study.blog.presentation.controller;

import com.study.blog.presentation.controller.request.CreatePostRequest;
import com.study.blog.presentation.controller.request.SearchPostRequest;
import com.study.blog.presentation.controller.request.UpdatePostRequest;
import com.study.blog.presentation.controller.response.PostListResponse;
import com.study.blog.presentation.controller.response.PostResponse;
import com.study.blog.business.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/posts")
@RequiredArgsConstructor
public class AdminPostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("postId") Long postId) {

        PostResponse post = postService.getPost(postId);

        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<Page<PostListResponse>> searchPostList(
                                                              @RequestParam(required = false) Long searchCategoryId,
                                                              @RequestParam(required = false) String searchTitle,
                                                              @RequestParam(required = false) String searchContent,
                                                              @RequestParam(required = false) Boolean searchStatus,
                                                              Pageable pageable) {
        SearchPostRequest searchPostRequest = new SearchPostRequest(searchCategoryId, searchTitle, searchContent, searchStatus);

        Page<PostListResponse> postList = postService.searchPostList(searchPostRequest, pageable);

        return ResponseEntity.ok(postList);
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        Long createdPostId = postService.createPost(createPostRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("createdPostId", createdPostId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @PathVariable("postId") Long postId,
            @RequestBody @Valid UpdatePostRequest updatePostRequest) {

        postService.updatePost(postId, updatePostRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/status")
    public ResponseEntity<Void> updatePostStatus(@PathVariable("postId") Long postId) {

        postService.updatePostStatus(postId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Long postId) {

        postService.deletePost(postId);

        return ResponseEntity.noContent().build();
    }

}
