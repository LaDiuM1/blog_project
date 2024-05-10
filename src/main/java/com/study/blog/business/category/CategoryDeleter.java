package com.study.blog.business.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CategoryDeleter {

    private final CategoryRepository categoryRepository;

    public void deleteCategory(Long id){
        boolean existingCategoryCheck = categoryRepository.existsById(id);

        if(!existingCategoryCheck) { throw new EntityNotFoundException(); }
        categoryRepository.deleteById(id);
    }

}
