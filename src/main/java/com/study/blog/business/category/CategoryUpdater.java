package com.study.blog.business.category;

import com.study.blog.business.category.data.CategoryUpdateData;
import com.study.blog.business.category.data.CategoryUpdateSequenceData;
import com.study.blog.business.category.repository.CategoryRepository;
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
    public void updateCategoryStatus(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(EntityNotFoundException::new);
        category.switchStatus();
    }

    public void updateCategorySequence(CategoryUpdateSequenceData updateSequenceData){
        LinkedHashSet<Long> idSet = updateSequenceData.getIdSet();

        boolean updateValid = categoryRepository.updateCategoryValid(idSet);
        if(updateValid){ throw new IllegalStateException(); }

        categoryRepository.updateCategorySequence(idSet);
    }

    @Transactional
    public void updateCategory(Long categoryId, CategoryUpdateData updateData){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(EntityNotFoundException::new);

        category.updateCategory(updateData.getName(), updateData.getDescription());
    }

}
