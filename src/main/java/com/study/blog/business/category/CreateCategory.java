package com.study.blog.business.category;

import com.study.blog.business.category.Category;
import com.study.blog.business.category.CategoryRepository;
import com.study.blog.presentation.controller.request.CreateCategoryRequest;
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