package com.study.blog.controller;

import com.study.blog.service.category.request.CreateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategorySequenceRequest;
import com.study.blog.infrastructure.persistence.repository.category.response.CategoryListResponse;
import com.study.blog.infrastructure.persistence.repository.category.response.CategoryResponse;
import com.study.blog.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/create")
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

        categoryService.createCategory(createCategoryRequest);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/update")
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody UpdateCategoryRequest request) {

        categoryService.updateCategory(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/status")
    public ResponseEntity<Void> updateCategoryStatus(@Param("id") Long id) {

        categoryService.updateCategoryStatus(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/sequence")
    public ResponseEntity<Void> updateCategorySequence(@Valid @RequestBody UpdateCategorySequenceRequest request) {

        categoryService.updateCategorySequence(request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.ok().build();
    }

}
