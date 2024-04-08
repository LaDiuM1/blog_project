package com.study.blog.domain.post;

import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.repository.PostRepository;
import com.study.blog.domain.post.request.UpdatePostRequest;
import com.study.blog.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class PostUpdater {

    private final PostQuery postQuery;
    private final PostCreator postCreator;
    private final PostTagUpdater postTagUpdater;

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request){
            Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(EntityNotFoundException::new);

            post.updatePost(category, request.getTitle(), request.getContent());
    }

    @Transactional
    public void updatePostStatus(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
        post.switchStatus();
    }

}
