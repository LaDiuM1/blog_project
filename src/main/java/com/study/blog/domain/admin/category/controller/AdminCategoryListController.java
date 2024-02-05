package com.study.blog.domain.admin.category.controller;

import com.study.blog.domain.admin.category.response.CategoryListResponse;
import com.study.blog.domain.admin.category.service.AdminCategoryService;
import com.study.blog.domain.admin.category.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryListController {

    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public ResponseEntity<List<CategoryListResponse>> getAdminCategoryList() {

        List<CategoryListResponse> categoryList = adminCategoryService.getAdminCategoryList();

        return ResponseEntity.ok(categoryList);
    }

}
