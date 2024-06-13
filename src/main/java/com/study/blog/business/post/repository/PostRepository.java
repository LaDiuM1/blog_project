package com.study.blog.business.post.repository;

import com.study.blog.business.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

}
