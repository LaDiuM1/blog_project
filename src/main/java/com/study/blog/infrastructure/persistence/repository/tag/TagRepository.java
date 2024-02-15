package com.study.blog.infrastructure.persistence.repository.tag;

import com.study.blog.infrastructure.persistence.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

    Optional<Tag> findTagByName(String name);

}
