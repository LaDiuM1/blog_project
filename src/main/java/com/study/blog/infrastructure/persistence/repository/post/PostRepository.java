package com.study.blog.infrastructure.persistence.repository.post;

import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{

}
