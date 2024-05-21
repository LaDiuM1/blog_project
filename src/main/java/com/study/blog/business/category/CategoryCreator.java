package com.study.blog.business.category;

import com.study.blog.business.category.data.CategoryCreateData;
import com.study.blog.business.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreator {

    private final CategoryRepository categoryRepository;

    public Long createCategory(CategoryCreateData createData) {
        int sequenceNumber = categoryRepository.getCreateSequenceNumber();

        Category category = new Category(
                createData.getName(),
                createData.getDescription(),
                sequenceNumber
        );

        return categoryRepository.save(category).getId();
    }
}