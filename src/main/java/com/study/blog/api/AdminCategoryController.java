package com.study.blog.api;

import com.study.blog.domain.category.request.CreateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategorySequenceRequest;
import com.study.blog.domain.category.response.CategoryListResponse;
import com.study.blog.domain.category.response.CategoryResponse;
import com.study.blog.domain.category.CategoryService;
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

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("categoryId") Long categoryId){
        CategoryResponse category = categoryService.getCategory(categoryId);

        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryListResponse>> getCategoryList() {
        List<CategoryListResponse> categoryList = categoryService.getCategoryList();

        return ResponseEntity.ok(categoryList);
    }

    @PostMapping
    public ResponseEntity<Long> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Long createdCategoryId = categoryService.createCategory(createCategoryRequest);

        return new ResponseEntity<>(createdCategoryId, HttpStatus.CREATED);
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
    public ResponseEntity<Void> updateCategoryStatus(@PathVariable("categoryId") Long categoryId) {
        categoryService.updateCategoryStatus(categoryId);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/sequence")
    public ResponseEntity<Void> updateCategorySequence(@RequestBody @Valid UpdateCategorySequenceRequest request) {
        categoryService.updateCategorySequence(request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }

}
