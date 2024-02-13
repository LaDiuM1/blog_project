package com.study.blog.infrastructure.persistence.repository.category;

import com.study.blog.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

    default CategoryEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
