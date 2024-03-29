package com.study.blog.persistence.repository.tag;

import com.study.blog.persistence.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

    List<Tag> findTagsByNameIn(Set<String> tagNames);

}
