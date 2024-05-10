package com.study.blog.presentation.controller;


import com.study.blog.business.tag.SearchTagDto;
import com.study.blog.business.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/tags")
@RequiredArgsConstructor
public class AdminTagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<SearchTagDto>> searchTag(@Valid String searchTagKeyword) {

        List<SearchTagDto> searchTagDtoList = tagService.searchTag(searchTagKeyword);

        return ResponseEntity.ok(searchTagDtoList);
    }


}
