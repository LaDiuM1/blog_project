package com.study.blog.admin.category.controller;

import com.study.blog.model.dto.category.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CreateCategoryController {

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {


        return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
    }

}
