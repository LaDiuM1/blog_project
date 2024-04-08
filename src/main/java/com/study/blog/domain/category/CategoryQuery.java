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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryQuery {

    private final CategoryRepository categoryRepository;

    public List<CategoryListResponse> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    public CategoryResponse getCategory(Long id){
        return categoryRepository.findByIdOrThrow(id).toResponse();
    }

}
