package com.study.blog.model.repository.category;

import com.study.blog.model.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
