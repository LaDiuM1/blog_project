package com.study.blog.domain.post;

import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.repository.PostRepository;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.post.response.PostResponse;
import com.study.blog.domain.tag.repository.Tag;
import com.study.blog.domain.tag.repository.TagRepository;
import com.study.blog.domain.tag.response.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostCreator {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createPost(CreatePostRequest request){
        boolean existingCategoryCheck = categoryRepository.existsById(request.getCategoryId());

        if(!existingCategoryCheck) { throw new EntityNotFoundException(); }

        Category categoryRef = new Category(request.getCategoryId());

        Post post = new Post(categoryRef, request.getTitle(), request.getContent());

        return postRepository.save(post).getId();
    }


}
