package com.study.blog.infrastructure.persistence.category;

import com.study.blog.infrastructure.entity.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Override
    Optional<CategoryEntity> findById(Long id);

    default CategoryEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
