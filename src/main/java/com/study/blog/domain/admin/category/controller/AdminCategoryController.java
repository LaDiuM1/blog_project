package com.study.blog.domain.admin.category.controller;

import com.study.blog.domain.admin.category.response.CategoryListResponse;
import com.study.blog.domain.admin.category.response.CategoryResponse;
import com.study.blog.domain.admin.category.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public ResponseEntity<List<CategoryListResponse>> getCategoryList() {

        List<CategoryListResponse> categoryList = adminCategoryService.getAdminCategoryList();

        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("id") Long id){

        CategoryResponse category = adminCategoryService.getCategory(id);

        return ResponseEntity.ok(category);

    }

}
