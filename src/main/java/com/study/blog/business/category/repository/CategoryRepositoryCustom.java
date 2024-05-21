package com.study.blog.business.category.repository;

import com.study.blog.business.category.dto.CategoryListDto;

import java.util.List;
import java.util.Set;

public interface CategoryRepositoryCustom {
    Integer getCreateSequenceNumber();
    List<CategoryListDto> getCategoryList();
    boolean updateCategoryValid(Set<Long> idSet);
    void updateCategorySequence(Set<Long> idSet);

}
