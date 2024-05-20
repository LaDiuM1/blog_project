package com.study.blog.business.tag.repository;

import com.study.blog.business.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

    List<Tag> findTagsByNameIn(Set<String> tagNames);

}
