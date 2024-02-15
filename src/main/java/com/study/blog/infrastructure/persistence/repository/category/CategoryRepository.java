package com.study.blog.infrastructure.persistence.repository.category;

import com.study.blog.infrastructure.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

    default Category findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
