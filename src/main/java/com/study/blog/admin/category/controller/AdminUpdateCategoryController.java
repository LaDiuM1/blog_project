package com.study.blog.admin.category.controller;

import com.study.blog.admin.category.service.AdminCategoryService;
import com.study.blog.admin.request.CreateCategoryRequest;
import com.study.blog.admin.request.UpdateCategoryRequest;
import com.study.blog.admin.request.UpdateCategorySequenceRequest;
import com.study.blog.admin.request.UpdateCategoryStatusRequest;
import com.study.blog.infrastructure.dto.category.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/category/update")
@RequiredArgsConstructor
public class AdminUpdateCategoryController {

    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public ResponseEntity<?> getCategory(@Param("id") Long id){

        CategoryDto category = adminCategoryService.getCategory(id);

        return ResponseEntity.ok(category);

    }

    @PostMapping
    public ResponseEntity<?> updateCategory(@Valid @RequestBody UpdateCategoryRequest request) {

        adminCategoryService.updateCategory(request);

        return new ResponseEntity<>("Category sequence updated successfully", HttpStatus.OK);
    }


    @PostMapping("/status")
    public ResponseEntity<?> updateCategoryStatus(@Valid @RequestBody UpdateCategoryStatusRequest request) {

        adminCategoryService.updateCategoryStatus(request);

        return new ResponseEntity<>("Category status updated successfully", HttpStatus.OK);
    }

    @PostMapping("/sequence")
    public ResponseEntity<?> updateCategorySequence(@Valid @RequestBody List<UpdateCategorySequenceRequest> requestList) {

        adminCategoryService.updateCategorySequence(requestList);

        return new ResponseEntity<>("Category sequence updated successfully", HttpStatus.OK);
    }



}
