package com.study.blog.domain.post.service;

import com.study.blog.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class DeletePost {

    private final PostRepository postRepository;

    public void deletePost(Long postId){
        boolean existingPostCheck = postRepository.existsById(postId);

        if(!existingPostCheck) { throw new EntityNotFoundException(); }

        postRepository.deleteById(postId);
    }

}
