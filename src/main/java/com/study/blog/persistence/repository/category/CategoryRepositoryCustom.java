package com.study.blog.persistence.repository.category;

import com.study.blog.persistence.repository.category.response.CategoryListResponse;
import com.study.blog.persistence.entity.Category;

import java.util.List;
import java.util.Set;


public interface CategoryRepositoryCustom {
    Category findByIdOrThrow(Long id);
    Integer getCreateSequenceNumber();
    List<CategoryListResponse> getAdminCategoryList();
    boolean updateCategoryValid(Set<Long> idSet);
    void updateCategorySequence(Set<Long> idSet);

}
