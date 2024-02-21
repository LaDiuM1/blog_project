package com.study.blog.controller.category;

import com.study.blog.service.category.CategoryService;
import com.study.blog.controller.category.request.UpdateCategoryRequest;
import com.study.blog.controller.category.request.UpdateCategorySequenceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/category/update")
@RequiredArgsConstructor
public class AdminUpdateCategoryController {

    private final CategoryService categoryService;

    @PutMapping
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody UpdateCategoryRequest request) {

        categoryService.updateCategory(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/status")
    public ResponseEntity<Void> updateCategoryStatus(@Param("id") Long id) {

        categoryService.updateCategoryStatus(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/sequence")
    public ResponseEntity<Void> updateCategorySequence(@Valid @RequestBody UpdateCategorySequenceRequest request) {

        categoryService.updateCategorySequence(request);

        return ResponseEntity.ok().build();
    }

}
