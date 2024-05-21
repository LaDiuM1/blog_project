package com.study.blog.business.post;

import com.study.blog.business.category.Category;
import com.study.blog.business.category.repository.CategoryRepository;
import com.study.blog.business.post.data.PostSearchData;
import com.study.blog.business.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class CreatePost {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public Post createPost(PostSearchData postSearchData){
        boolean existingCategoryCheck = categoryRepository.existsById(postSearchData.getCategoryId());

        if(!existingCategoryCheck) { throw new EntityNotFoundException(); }

        Category categoryRef = new Category(postSearchData.getCategoryId());

        Post post = new Post(categoryRef, postSearchData.getTitle(), postSearchData.getContent());

        return postRepository.save(post);
    }


}
