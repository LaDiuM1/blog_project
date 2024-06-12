package com.study.blog.business.post;

import com.study.blog.business.category.Category;
import com.study.blog.business.category.repository.CategoryRepository;
import com.study.blog.business.post.data.PostUpdateData;
import com.study.blog.business.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class PostUpdater {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public Post updatePost(Long postId, PostUpdateData updateData){
            Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
            Category category = categoryRepository.findById(updateData.getCategoryId()).orElseThrow(EntityNotFoundException::new);

            post.updatePost(category, updateData.getTitle(), updateData.getContent());

            return post;
    }

    public void updatePostStatus(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
        post.switchStatus();
    }


}
