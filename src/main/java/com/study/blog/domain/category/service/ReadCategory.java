package com.study.blog.domain.category.service;

import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.category.response.CategoryListResponse;
import com.study.blog.domain.category.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReadCategory {

    private final CategoryRepository categoryRepository;

    public List<CategoryListResponse> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    public CategoryResponse getCategory(Long id){
        return categoryRepository.findByIdOrThrow(id).toResponse();
    }

}
