package com.study.blog.domain.category;

import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.category.request.CreateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategorySequenceRequest;
import com.study.blog.domain.category.response.CategoryListResponse;
import com.study.blog.domain.category.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryCreator {

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