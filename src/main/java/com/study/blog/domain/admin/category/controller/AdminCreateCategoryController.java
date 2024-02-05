package com.study.blog.domain.admin.category.controller;

import com.study.blog.domain.admin.category.service.AdminCategoryService;
import com.study.blog.domain.admin.category.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/category/create")
@RequiredArgsConstructor
public class AdminCreateCategoryController {

    private final AdminCategoryService adminCategoryService;

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

        adminCategoryService.createCategory(createCategoryRequest);

        return ResponseEntity.ok().build();
    }

}
