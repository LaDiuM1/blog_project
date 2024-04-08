package com.study.blog.api;

import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.post.response.PostResponse;
import com.study.blog.domain.post.PostService;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/posts")
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

    @PostMapping
    public ResponseEntity<Long> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        Long createdPostId = postService.createPost(createPostRequest);

        return new ResponseEntity<>(createdPostId, HttpStatus.CREATED);
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
