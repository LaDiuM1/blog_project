package com.study.blog.infrastructure.persistence.repository.category;

import com.study.blog.domain.admin.category.response.CategoryListResponse;

import java.util.List;


public interface CategoryRepositoryCustom {
    Integer getCreateSequenceNumber();
    List<CategoryListResponse> getAdminCategoryList();

}
