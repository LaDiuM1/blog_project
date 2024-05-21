package com.study.blog.business.category;

import com.study.blog.business.category.data.CategoryUpdateData;
import com.study.blog.business.category.repository.CategoryRepository;
import com.study.blog.presentation.controller.request.CategoryUpdateSequenceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.LinkedHashSet;

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

    public void updateCategorySequence(CategoryUpdateSequenceRequest request){
        LinkedHashSet<Long> idSet = request.getIdSet();

        boolean updateValid = categoryRepository.updateCategoryValid(idSet);
        if(updateValid){ throw new IllegalStateException(); }

        categoryRepository.updateCategorySequence(idSet);
    }

    @Transactional
    public void updateCategory(Long categoryId, CategoryUpdateData categoryUpdateData){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(EntityNotFoundException::new);

        category.updateCategory(categoryUpdateData.getName(), categoryUpdateData.getDescription());
    }

}
