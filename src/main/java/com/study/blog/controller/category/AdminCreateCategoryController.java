package com.study.blog.controller.category;

import com.study.blog.service.category.CategoryService;
import com.study.blog.controller.category.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
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

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

        categoryService.createCategory(createCategoryRequest);

        return ResponseEntity.ok().build();
    }

}
