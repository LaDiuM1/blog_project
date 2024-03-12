package com.study.blog.infrastructure.persistence.repository.comment;

import com.study.blog.infrastructure.persistence.entity.Comment;
import com.study.blog.infrastructure.persistence.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

}
