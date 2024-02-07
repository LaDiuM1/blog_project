package com.study.blog.domain.admin.category.controller;

import com.study.blog.domain.admin.category.request.UpdateCategoryRequest;
import com.study.blog.domain.admin.category.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/category/delete")
@RequiredArgsConstructor
public class AdminDeleteCategoryController {

    private final AdminCategoryService adminCategoryService;

    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@Param("id") Long id) {

        adminCategoryService.deleteCategory(id);

        return ResponseEntity.ok().build();
    }

}
