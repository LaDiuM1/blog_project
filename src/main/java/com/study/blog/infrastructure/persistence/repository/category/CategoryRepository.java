package com.study.blog.infrastructure.persistence.repository.category;

import com.study.blog.infrastructure.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

}
