package com.study.blog.infrastructure.persistence.repository.tag;

import com.study.blog.infrastructure.persistence.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

    default Tag findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
