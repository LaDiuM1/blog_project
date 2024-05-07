package com.study.blog.business.post;

import com.study.blog.business.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
