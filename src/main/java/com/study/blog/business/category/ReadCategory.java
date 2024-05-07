package com.study.blog.business.category;

import com.study.blog.business.category.CategoryRepository;
import com.study.blog.presentation.controller.response.CategoryListResponse;
import com.study.blog.presentation.controller.response.CategoryResponse;
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
