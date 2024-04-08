package com.study.blog.domain.category.repository;

import com.study.blog.domain.category.response.CategoryListResponse;

import java.util.List;
import java.util.Set;


public interface CategoryRepositoryCustom {
    Category findByIdOrThrow(Long id);
    Integer getCreateSequenceNumber();
    List<CategoryListResponse> getCategoryList();
    boolean updateCategoryValid(Set<Long> idSet);
    void updateCategorySequence(Set<Long> idSet);

}
