package com.study.blog.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

}
