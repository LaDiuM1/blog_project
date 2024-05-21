package com.study.blog.business.post;

import com.study.blog.business.category.Category;
import com.study.blog.business.category.repository.CategoryRepository;
import com.study.blog.business.post.repository.PostRepository;
import com.study.blog.presentation.controller.request.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UpdatePost {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Post updatePost(Long postId, PostUpdateRequest request){
            Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(EntityNotFoundException::new);

            post.updatePost(category, request.getTitle(), request.getContent());

            return post;
    }

    @Transactional
    public void updatePostStatus(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
        post.switchStatus();
    }

}
