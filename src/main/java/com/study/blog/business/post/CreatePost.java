package com.study.blog.business.post;

import com.study.blog.business.category.Category;
import com.study.blog.business.category.CategoryRepository;
import com.study.blog.business.post.Post;
import com.study.blog.business.post.PostRepository;
import com.study.blog.presentation.controller.request.CreatePostRequest;
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
