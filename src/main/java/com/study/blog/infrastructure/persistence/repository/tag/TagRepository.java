package com.study.blog.infrastructure.persistence.repository.tag;

import com.study.blog.infrastructure.persistence.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

    List<Tag> findTagsByNameIn(Set<String> tagNames);

}
