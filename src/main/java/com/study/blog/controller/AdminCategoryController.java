package com.study.blog.controller;

import com.study.blog.service.category.request.CreateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategorySequenceRequest;
import com.study.blog.persistence.repository.category.response.CategoryListResponse;
import com.study.blog.persistence.repository.category.response.CategoryResponse;
import com.study.blog.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryListResponse>> getCategoryList() {

        List<CategoryListResponse> categoryList = categoryService.getAdminCategoryList();

        return ResponseEntity.ok(categoryList);
    }

    @PostMapping
    public ResponseEntity<Long> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Long createdCategoryId = categoryService.createCategory(createCategoryRequest);
        return new ResponseEntity<>(createdCategoryId, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("categoryId") Long categoryId){

        CategoryResponse category = categoryService.getCategory(categoryId);

        return ResponseEntity.ok(category);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid UpdateCategoryRequest request
    ) {

        categoryService.updateCategory(categoryId, request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{categoryId}/status")
    public ResponseEntity<Void> updateCategoryStatus(@Param("id") Long id) {
        categoryService.updateCategoryStatus(id);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/{categoryId}/sequence")
    public ResponseEntity<Void> updateCategorySequence(@Valid @RequestBody UpdateCategorySequenceRequest request) {

        categoryService.updateCategorySequence(request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<Void> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
