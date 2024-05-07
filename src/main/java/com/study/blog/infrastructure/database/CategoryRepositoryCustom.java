package com.study.blog.infrastructure.database;

import com.study.blog.business.category.Category;
import com.study.blog.presentation.controller.response.CategoryListResponse;

import java.util.List;
import java.util.Set;


public interface CategoryRepositoryCustom {
    Category findByIdOrThrow(Long id);
    Integer getCreateSequenceNumber();
    List<CategoryListResponse> getCategoryList();
    boolean updateCategoryValid(Set<Long> idSet);
    void updateCategorySequence(Set<Long> idSet);

}
