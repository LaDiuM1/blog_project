package com.study.blog.presentation.controller;

import com.study.blog.business.post.PostService;
import com.study.blog.business.post.dto.PostDto;
import com.study.blog.business.post.dto.PostListDto;
import com.study.blog.presentation.controller.request.PostCreateRequest;
import com.study.blog.presentation.controller.request.PostSearchRequest;
import com.study.blog.presentation.controller.request.PostUpdateRequest;
import com.study.blog.presentation.controller.response.CreatedResponse;
import com.study.blog.presentation.controller.response.SuccessfulResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<PostDto> getPost(@PathVariable("postId") Long postId) {
        PostDto post = postService.getPost(postId);

        return SuccessfulResponse.response(post);
    }

    @GetMapping
    public ResponseEntity<Page<PostListDto>> searchPostList(@RequestParam(required = false) Long searchCategoryId,
                                                            @RequestParam(required = false) String searchTitle,
                                                            @RequestParam(required = false) String searchContent,
                                                            @RequestParam(required = false) Boolean searchStatus,
                                                            Pageable pageable) {
        PostSearchRequest postSearchRequest = new PostSearchRequest(searchCategoryId, searchTitle, searchContent, searchStatus);

        Page<PostListDto> postList = postService.searchPostList(postSearchRequest, pageable);

        return SuccessfulResponse.response(postList);
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createPost(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        Long createdPostId = postService.createPost(postCreateRequest.toData());

        return CreatedResponse.response("Post", createdPostId);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable("postId") Long postId,
                                           @RequestBody @Valid PostUpdateRequest postUpdateRequest) {

        postService.updatePost(postId, postUpdateRequest);

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
