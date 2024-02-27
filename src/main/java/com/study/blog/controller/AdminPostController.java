package com.study.blog.controller;

import com.study.blog.service.post.request.CreatePostRequest;
import com.study.blog.service.post.request.PostListRequest;
import com.study.blog.service.post.request.UpdatePostRequest;
import com.study.blog.infrastructure.persistence.repository.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.repository.post.response.PostResponse;
import com.study.blog.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

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
    public ResponseEntity<Page<PostListResponse>> getPostList(
                                                              @RequestParam(required = false) Long searchCategoryId,
                                                              @RequestParam(required = false) String searchKeyword,
                                                              @RequestParam(required = false) Boolean searchStatus,
                                                              @RequestParam(required = false) Set<Long> testIds, // 테스트 용도
                                                              Pageable pageable) {
        PostListRequest postListRequest = new PostListRequest();
        postListRequest.setSearchCategoryId(searchCategoryId);
        postListRequest.setSearchKeyword(searchKeyword);
        postListRequest.setSearchStatus(searchStatus);

        System.out.println("testIds = " + testIds);
        // 사용자 요청 발생 시 ConversionService가 요청을 분석, 쿼리스트링이 있을 경우 구문 분석을 진행하며
        // 쉼표 형태 발견 시 Converter와 Formatter 구현체가 쉼표를 배열 자료구조 형태로 변환하는 작업을 진행


        Page<PostListResponse> postList = postService.getPostList(postListRequest, pageable);

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
    public ResponseEntity<Void> updatePostStatus(@Param("id") Long id) {

        postService.updatePostStatus(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePost(@Param("id") Long id) {

        postService.deletePost(id);

        return ResponseEntity.ok().build();
    }

}
