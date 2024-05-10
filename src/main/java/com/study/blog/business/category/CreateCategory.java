package com.study.blog.business.category;

import com.study.blog.business.category.data.CategoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategory {

    private final CategoryRepository categoryRepository;

    public Long createCategory(CategoryData categoryData) {
        int sequenceNumber = categoryRepository.getCreateSequenceNumber();

        Category category = new Category(
                categoryData.getName(),
                categoryData.getDescription(),
                sequenceNumber
        );

        return categoryRepository.save(category).getId();
    }
}