package com.study.blog.infrastructure.persistence.category;

import com.study.blog.infrastructure.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
