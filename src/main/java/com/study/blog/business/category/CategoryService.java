package com.study.blog.business.category;

import com.study.blog.business.category.data.CategoryData;
import com.study.blog.business.category.dto.CategoryDto;
import com.study.blog.business.category.dto.CategoryListDto;
import com.study.blog.presentation.controller.request.UpdateCategoryRequest;
import com.study.blog.presentation.controller.request.UpdateCategorySequenceRequest;
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

    public Long createCategory(CategoryData categoryData){
        return categoryCreator.createCategory(categoryData);
    }

    public void updateCategoryStatus(Long id){
        categoryUpdater.updateCategoryStatus(id);
    }

    public void updateCategorySequence(UpdateCategorySequenceRequest request){
        categoryUpdater.updateCategorySequence(request);
    }

    public void updateCategory(Long categoryId, UpdateCategoryRequest request){
        categoryUpdater.updateCategory(categoryId, request);
    }

    public void deleteCategory(Long id){
        categoryDeleter.deleteCategory(id);
    }

}
