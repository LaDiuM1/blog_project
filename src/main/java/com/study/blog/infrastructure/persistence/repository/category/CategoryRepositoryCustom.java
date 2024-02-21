package com.study.blog.infrastructure.persistence.repository.category;

import com.study.blog.service.category.response.CategoryListResponse;
import com.study.blog.infrastructure.persistence.entity.Category;

import java.util.List;
import java.util.Set;


public interface CategoryRepositoryCustom {
    Category findByIdOrThrow(Long id);
    Integer getCreateSequenceNumber();
    List<CategoryListResponse> getAdminCategoryList();
    boolean updateCategoryValid(Set<Long> idSet);
    void updateCategorySequence(Set<Long> idSet);

}
