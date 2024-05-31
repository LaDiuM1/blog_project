package com.study.blog.presentation.controller;


import com.study.blog.business.tag.TagService;
import com.study.blog.business.tag.dto.SearchTagDto;
import com.study.blog.presentation.controller.response.SuccessfulResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<SearchTagDto>> searchTag(@RequestParam("keyword") String searchTagKeyword) {

        List<SearchTagDto> searchTagDtoList = tagService.searchTag(searchTagKeyword);

        return SuccessfulResponse.response(searchTagDtoList);
    }


}
