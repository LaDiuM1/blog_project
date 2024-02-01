package com.study.blog.admin.category.controller;

import com.study.blog.admin.category.service.AdminCategoryService;
import com.study.blog.infrastructure.dto.category.CategoryDto;
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
    public ResponseEntity<?> getAdminCategoryList(/*@RequestBody User user*/) {

        List<CategoryDto> categoryList = adminCategoryService.getAdminCategoryList();

        return ResponseEntity.ok(categoryList);
    }

}
