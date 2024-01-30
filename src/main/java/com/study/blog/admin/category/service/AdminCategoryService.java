package com.study.blog.admin.category.service;

import com.study.blog.model.dto.category.CreateCategoryRequest;
import com.study.blog.model.repository.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final JpaCategoryRepository categoryRepository;

    public String createCategory (CreateCategoryRequest createCategoryRequest){


    }

}
