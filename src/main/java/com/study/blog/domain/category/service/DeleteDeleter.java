package com.study.blog.domain.category.service;

import com.study.blog.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class DeleteDeleter {

    private final CategoryRepository categoryRepository;

    public void deleteCategory(Long id){
        boolean existingCategoryCheck = categoryRepository.existsById(id);

        if(!existingCategoryCheck) { throw new EntityNotFoundException(); }
        categoryRepository.deleteById(id);
    }

}
