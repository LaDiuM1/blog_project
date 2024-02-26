package com.study.blog.controller.post;

import com.study.blog.controller.post.request.PostListRequest;
import com.study.blog.service.post.response.PostListResponse;
import com.study.blog.service.post.response.PostResponse;
import com.study.blog.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                              @RequestParam(required = false) Set<Long> searchCategoryIds,
                                                              @RequestParam(required = false) String searchKeyword,
                                                              @RequestParam(required = false) Boolean searchStatus,
                                                              Pageable pageable) {
        // 배열값을 쿼리스트링으로 받는 방법 확인
        PostListRequest postListRequest = new PostListRequest();
        postListRequest.setSearchCategoryIds(searchCategoryIds);
        postListRequest.setSearchKeyword(searchKeyword);
        postListRequest.setSearchStatus(searchStatus);

        Page<PostListResponse> postList = postService.getPostList(postListRequest, pageable);

        return ResponseEntity.ok(postList);
    }


}
