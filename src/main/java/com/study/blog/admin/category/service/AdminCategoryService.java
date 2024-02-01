package com.study.blog.admin.category.service;

import com.study.blog.admin.category.repository.CategoryRepository;
import com.study.blog.admin.request.CreateCategoryRequest;
import com.study.blog.infrastructure.dto.category.CategoryDto;
import com.study.blog.infrastructure.entity.category.Category;
import com.study.blog.infrastructure.persistence.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final JpaCategoryRepository jpaCategoryRepository;

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAdminCategoryList(/*User user*/) {
        return categoryRepository.getAdminCategoryList();
    }

    public void createCategory(CreateCategoryRequest createCategoryRequest){

        int sequenceNumber = categoryRepository.getCreateSequenceNumber();

        Category categoryEntity = new Category(
                createCategoryRequest.getName(),
                createCategoryRequest.getDescription(),
                sequenceNumber
        );

        jpaCategoryRepository.save(categoryEntity);

    }

}
