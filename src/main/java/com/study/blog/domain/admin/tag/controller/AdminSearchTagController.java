package com.study.blog.domain.admin.tag.controller;


import com.study.blog.domain.admin.tag.response.SearchTagResponse;
import com.study.blog.domain.admin.tag.service.AdminTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/tag/search")
@RequiredArgsConstructor
public class AdminSearchTagController {

    private final AdminTagService adminTagService;

    @GetMapping
    public ResponseEntity<List<SearchTagResponse>> searchTag(@Param("keyword") String searchTagKeyword) {

        List<SearchTagResponse> searchTagResponseList = adminTagService.searchTag(searchTagKeyword);

        return ResponseEntity.ok(searchTagResponseList);

    }


}
