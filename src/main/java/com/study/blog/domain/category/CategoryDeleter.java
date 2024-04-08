package com.study.blog.domain.category;

import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.category.request.CreateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategorySequenceRequest;
import com.study.blog.domain.category.response.CategoryListResponse;
import com.study.blog.domain.category.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
