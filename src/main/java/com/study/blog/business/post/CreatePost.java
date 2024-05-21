package com.study.blog.business.post;

import com.study.blog.business.category.Category;
import com.study.blog.business.category.repository.CategoryRepository;
import com.study.blog.business.post.data.PostCreateData;
import com.study.blog.business.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class CreatePost {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public Post createPost(PostCreateData createData){
        boolean existingCategoryCheck = categoryRepository.existsById(createData.getCategoryId());

        if(!existingCategoryCheck) { throw new EntityNotFoundException(); }

        Category categoryRef = new Category(createData.getCategoryId());

        Post post = new Post(categoryRef, createData.getTitle(), createData.getContent());

        return postRepository.save(post);
    }


}
