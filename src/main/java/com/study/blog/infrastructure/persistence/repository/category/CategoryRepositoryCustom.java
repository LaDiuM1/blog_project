package com.study.blog.infrastructure.persistence.repository.category;

import com.study.blog.domain.admin.category.response.CategoryListResponse;

import java.util.List;
import java.util.Set;


public interface CategoryRepositoryCustom {
    Integer getCreateSequenceNumber();
    List<CategoryListResponse> getAdminCategoryList();
    boolean updateCategoryValid(Set<Long> idSet);
    void updateCategorySequence(Set<Long> idSet);


}
