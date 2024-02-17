package com.study.blog.domain.admin.post.service;

import com.study.blog.domain.admin.post.request.CreatePostRequest;
import com.study.blog.domain.admin.post.request.PostListRequest;
import com.study.blog.domain.admin.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.entity.Post;
import com.study.blog.infrastructure.persistence.entity.Tag;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import com.study.blog.infrastructure.persistence.repository.post.PostRepository;
import com.study.blog.infrastructure.persistence.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

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

        if(Optional.ofNullable(request.getTagNames()).isPresent()){
            request.getTagNames().forEach(tagName -> {
                Tag tag = tagRepository.findTagByName(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.getTags().add(tag);
            });
        }
        postRepository.save(post);

    }

    public Page<PostListResponse> getPostList (PostListRequest request, Pageable pageable){

        Set<Long> searchCategoryIds = request.getSearchCategoryIds();
        String searchKeyword = request.getSearchKeyword();
        Boolean searchStatus = request.getSearchStatus();

        return postRepository.getPostList(searchCategoryIds, searchKeyword, searchStatus, pageable);

    }

}
