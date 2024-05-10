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

    private final CreateCategory createCategory;
    private final ReadCategory readCategory;
    private final UpdateCategory updateCategory;
    private final DeleteDeleter deleteDeleter;

    public List<CategoryListDto> getCategoryList() {
        return readCategory.getCategoryList();
    }

    public CategoryDto getCategory(Long id){
        return readCategory.getCategory(id);
    }

    public Long createCategory(CategoryData categoryData){
        return createCategory.createCategory(categoryData);
    }

    public void updateCategoryStatus(Long id){
        updateCategory.updateCategoryStatus(id);
    }

    public void updateCategorySequence(UpdateCategorySequenceRequest request){
        updateCategory.updateCategorySequence(request);
    }

    public void updateCategory(Long categoryId, UpdateCategoryRequest request){
        updateCategory.updateCategory(categoryId, request);
    }

    public void deleteCategory(Long id){
        deleteDeleter.deleteCategory(id);
    }

}
