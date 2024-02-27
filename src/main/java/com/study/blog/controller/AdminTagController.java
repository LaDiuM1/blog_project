package com.study.blog.controller;


import com.study.blog.service.tag.response.SearchTagResponse;
import com.study.blog.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/tag")
@RequiredArgsConstructor
public class AdminTagController {

    private final TagService tagService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchTagResponse>> searchTag(@Param("keyword") String searchTagKeyword) {

        List<SearchTagResponse> searchTagResponseList = tagService.searchTag(searchTagKeyword);

        return ResponseEntity.ok(searchTagResponseList);
    }


}
