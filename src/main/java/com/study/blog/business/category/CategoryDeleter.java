package com.study.blog.business.category;

import com.study.blog.business.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CategoryDeleter {

    private final CategoryRepository categoryRepository;

    public void deleteCategory(Long categoryId){
        boolean existingCategoryCheck = categoryRepository.existsById(categoryId);

        if(!existingCategoryCheck) { throw new EntityNotFoundException(); }
        categoryRepository.deleteById(categoryId);
    }

}
