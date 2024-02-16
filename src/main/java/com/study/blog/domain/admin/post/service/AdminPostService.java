package com.study.blog.domain.admin.post.service;

import com.study.blog.domain.admin.post.request.CreatePostRequest;
import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.entity.Post;
import com.study.blog.infrastructure.persistence.entity.Tag;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import com.study.blog.infrastructure.persistence.repository.post.PostRepository;
import com.study.blog.infrastructure.persistence.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminPostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void createPost(CreatePostRequest request){
        Category category = categoryRepository.findByIdOrThrow(request.getCategoryId());

        Post post = new Post(category, request.getTitle(), request.getContent());

        if(Optional.ofNullable(request.getTagNameSet()).isPresent()){
            request.getTagNameSet().forEach(tagName -> {
                Tag tag = tagRepository.findTagByName(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.getTags().add(tag);
            });
        }
        postRepository.save(post);

    }

}
