package com.study.blog.domain.admin.category.controller;

import com.study.blog.domain.admin.category.service.AdminCategoryService;
import com.study.blog.domain.admin.category.request.UpdateCategoryRequest;
import com.study.blog.domain.admin.category.request.UpdateCategorySequenceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/category/update")
@RequiredArgsConstructor
public class AdminUpdateCategoryController {

    private final AdminCategoryService adminCategoryService;



    @PutMapping
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody UpdateCategoryRequest request) {

        adminCategoryService.updateCategory(request);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/status")
    public ResponseEntity<Void> updateCategoryStatus(@Param("id") Long id) {

        adminCategoryService.updateCategoryStatus(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/sequence")
    public ResponseEntity<Void> updateCategorySequence(@Valid @RequestBody UpdateCategorySequenceRequest request) {

        adminCategoryService.updateCategorySequence(request);

        return ResponseEntity.ok().build();
    }



}
