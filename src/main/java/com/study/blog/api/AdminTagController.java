package com.study.blog.api;


import com.study.blog.domain.tag.response.SearchTagResponse;
import com.study.blog.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/tags")
@RequiredArgsConstructor
public class AdminTagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<SearchTagResponse>> searchTag(@Valid String searchTagKeyword) {

        List<SearchTagResponse> searchTagResponseList = tagService.searchTag(searchTagKeyword);

        return ResponseEntity.ok(searchTagResponseList);
    }


}
