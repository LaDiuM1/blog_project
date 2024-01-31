package com.study.blog.admin.category.service;

import com.study.blog.admin.category.repository.CategoryRepository;
import com.study.blog.model.dto.category.CreateCategoryRequest;
import com.study.blog.model.entity.category.Category;
import com.study.blog.model.persistence.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final JpaCategoryRepository jpaCategoryRepository;

    private final CategoryRepository categoryRepository;

    public void createCategory (CreateCategoryRequest createCategoryRequest){

        int sequenceNumber = categoryRepository.getCreateSequenceNumber();

        Category categoryEntity = new Category(
                createCategoryRequest.getName(),
                createCategoryRequest.getDescription(),
                sequenceNumber
        );

        jpaCategoryRepository.save(categoryEntity);

    }

}
