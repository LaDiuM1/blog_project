package com.study.blog.controller.category;

import com.study.blog.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/category/delete")
@RequiredArgsConstructor
public class AdminDeleteCategoryController {

    private final CategoryService categoryService;

    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@Param("id") Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.ok().build();
    }

}
