package com.study.blog.domain.post.service;

import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.repository.PostRepository;
import com.study.blog.domain.post.request.CreatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class CreatePost {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public Post createPost(CreatePostRequest request){
        boolean existingCategoryCheck = categoryRepository.existsById(request.getCategoryId());

        if(!existingCategoryCheck) { throw new EntityNotFoundException(); }

        Category categoryRef = new Category(request.getCategoryId());

        Post post = new Post(categoryRef, request.getTitle(), request.getContent());

        return postRepository.save(post);
    }


}
