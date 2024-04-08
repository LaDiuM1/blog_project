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
public class CategoryUpdater {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void updateCategoryStatus(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        category.switchStatus();
    }

    public void updateCategorySequence(UpdateCategorySequenceRequest request){
        LinkedHashSet<Long> idSet = request.getIdSet();

        boolean updateValid = categoryRepository.updateCategoryValid(idSet);
        if(updateValid){ throw new IllegalStateException(); }

        categoryRepository.updateCategorySequence(idSet);
    }

    @Transactional
    public void updateCategory(Long categoryId, UpdateCategoryRequest request){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(EntityNotFoundException::new);

        category.updateCategory(request.getName(), request.getDescription());
    }

}
