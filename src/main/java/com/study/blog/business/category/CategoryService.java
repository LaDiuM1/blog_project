package com.study.blog.business.category;

import com.study.blog.business.category.data.CategoryCreateData;
import com.study.blog.business.category.data.CategoryUpdateData;
import com.study.blog.business.category.data.CategoryUpdateSequenceData;
import com.study.blog.business.category.dto.CategoryDto;
import com.study.blog.business.category.dto.CategoryListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryCreator categoryCreator;
    private final CategoryReader categoryReader;
    private final CategoryUpdater categoryUpdater;
    private final CategoryDeleter categoryDeleter;

    public List<CategoryListDto> getCategoryList() {
        return categoryReader.getCategoryList();
    }

    public CategoryDto getCategory(Long categoryId){
        return categoryReader.getCategory(categoryId);
    }

    public Long createCategory(CategoryCreateData createData){
        return categoryCreator.createCategory(createData);
    }

    public void updateCategoryStatus(Long categoryId){
        categoryUpdater.updateCategoryStatus(categoryId);
    }

    public void updateCategorySequence(CategoryUpdateSequenceData updateSequenceData){
        categoryUpdater.updateCategorySequence(updateSequenceData);
    }

    public void updateCategory(Long categoryId, CategoryUpdateData updateData){
        categoryUpdater.updateCategory(categoryId, updateData);
    }

    public void deleteCategory(Long categoryId){
        categoryDeleter.deleteCategory(categoryId);
    }

}
