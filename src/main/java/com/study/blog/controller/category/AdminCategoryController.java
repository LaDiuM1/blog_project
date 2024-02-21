package com.study.blog.controller.category;

import com.study.blog.service.category.response.CategoryListResponse;
import com.study.blog.service.category.response.CategoryResponse;
import com.study.blog.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryListResponse>> getCategoryList() {

        List<CategoryListResponse> categoryList = categoryService.getAdminCategoryList();

        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("id") Long id){

        CategoryResponse category = categoryService.getCategory(id);

        return ResponseEntity.ok(category);

    }

}
