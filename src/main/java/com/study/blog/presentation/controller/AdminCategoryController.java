package com.study.blog.presentation.controller;

import com.study.blog.business.category.CategoryService;
import com.study.blog.business.category.dto.CategoryDto;
import com.study.blog.business.category.dto.CategoryListDto;
import com.study.blog.presentation.controller.request.CreateCategoryRequest;
import com.study.blog.presentation.controller.request.UpdateCategoryRequest;
import com.study.blog.presentation.controller.request.UpdateCategorySequenceRequest;
import com.study.blog.presentation.controller.response.CreatedResponse;
import com.study.blog.presentation.controller.response.SuccessfulResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Long categoryId){
        CategoryDto category = categoryService.getCategory(categoryId);

        return SuccessfulResponse.response(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryListDto>> getCategoryList() {
        List<CategoryListDto> categoryList = categoryService.getCategoryList();

        return SuccessfulResponse.response(categoryList);
    }

    @PostMapping
    public ResponseEntity<Map<String,Long>> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Long createdCategoryId = categoryService.createCategory(createCategoryRequest.toData());

        return CreatedResponse.response("Category",createdCategoryId);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid UpdateCategoryRequest request
    ) {
        categoryService.updateCategory(request.toData());

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
