package com.study.blog.domain.category.service;

import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.category.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategory {

    private final CategoryRepository categoryRepository;

    public Long createCategory(CreateCategoryRequest request) {
        int sequenceNumber = categoryRepository.getCreateSequenceNumber();

        Category category = new Category(
                request.getName(),
                request.getDescription(),
                sequenceNumber
        );

        return categoryRepository.save(category).getId();
    }
}