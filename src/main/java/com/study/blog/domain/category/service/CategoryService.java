package com.study.blog.domain.category.service;

import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.category.request.CreateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategorySequenceRequest;
import com.study.blog.domain.category.response.CategoryListResponse;
import com.study.blog.domain.category.response.CategoryResponse;
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

    public List<CategoryListResponse> getCategoryList() {
        return readCategory.getCategoryList();
    }

    public CategoryResponse getCategory(Long id){
        return readCategory.getCategory(id);
    }

    public Long createCategory(CreateCategoryRequest request){
        return createCategory.createCategory(request);
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
