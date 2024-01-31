package com.study.blog.model.persistence.category;

import com.study.blog.model.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
