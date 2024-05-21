package com.study.blog.business.category;

import com.study.blog.business.category.data.CategoryCreateData;
import com.study.blog.business.category.data.CategoryUpdateData;
import com.study.blog.business.category.dto.CategoryDto;
import com.study.blog.business.category.dto.CategoryListDto;
import com.study.blog.presentation.controller.request.CategoryUpdateSequenceRequest;
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

    public CategoryDto getCategory(Long id){
        return categoryReader.getCategory(id);
    }

    public Long createCategory(CategoryCreateData categoryCreateData){
        return categoryCreator.createCategory(categoryCreateData);
    }

    public void updateCategoryStatus(Long id){
        categoryUpdater.updateCategoryStatus(id);
    }

    public void updateCategorySequence(CategoryUpdateSequenceRequest request){
        categoryUpdater.updateCategorySequence(request);
    }

    public void updateCategory(Long categoryId, CategoryUpdateData categoryUpdateData){
        categoryUpdater.updateCategory(categoryId, categoryUpdateData);
    }

    public void deleteCategory(Long id){
        categoryDeleter.deleteCategory(id);
    }

}
