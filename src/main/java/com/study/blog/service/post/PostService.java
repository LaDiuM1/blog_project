package com.study.blog.service.post;

import com.study.blog.service.post.request.CreatePostRequest;
import com.study.blog.service.post.request.PostListRequest;
import com.study.blog.service.post.request.UpdatePostRequest;
import com.study.blog.infrastructure.persistence.repository.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.repository.post.response.PostResponse;
import com.study.blog.infrastructure.persistence.repository.tag.response.TagResponse;
import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.entity.Post;
import com.study.blog.infrastructure.persistence.entity.Tag;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import com.study.blog.infrastructure.persistence.repository.post.PostRepository;
import com.study.blog.infrastructure.persistence.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    private void postAddTags(Post post, Set<String> tagNames){
        List<Tag> existingTags = tagRepository.findTagsByNameIn(tagNames);
        Set<Tag> tags = new HashSet<>();

        Map<String, Tag> existingTagsMap = existingTags.stream()
                .collect(Collectors.toMap(Tag::getName, Function.identity()));

        Set<Tag> newTags = new HashSet<>();
        for (String tagName : tagNames) {
            if (existingTagsMap.containsKey(tagName)) {
                tags.add(existingTagsMap.get(tagName));
            }
            else {
                newTags.add(new Tag(tagName));
            }
        }
        tags.addAll(tagRepository.saveAll(newTags));
        post.setTags(tags);
    }

    @Transactional
    public void createPost(CreatePostRequest request){
        boolean categoryIdCheck = categoryRepository.existsById(request.getCategoryId());
        if(!categoryIdCheck) { throw new EntityNotFoundException(); }

        Category categoryRef = new Category(request.getCategoryId());

        Post post = new Post(categoryRef, request.getTitle(), request.getContent());

        if(Optional.ofNullable(request.getTagNames()).isPresent()){
            if(!request.getTagNames().isEmpty()){
                postAddTags(post, request.getTagNames());
            }
        }

        postRepository.save(post);
    }

    public Page<PostListResponse> searchPostList(PostListRequest request, Pageable pageable){
        Long searchCategoryId = request.getSearchCategoryId();
        String searchKeyword = request.getSearchKeyword();
        Boolean searchStatus = request.getSearchStatus();

        Page<PostListResponse> postList = postRepository.searchPostAndCommentCountList(searchCategoryId, searchKeyword, searchStatus, pageable);
        Set<Long> postIds = postList.getContent().stream().map(PostListResponse::getId).collect(Collectors.toSet());

        Map<Long, List<TagResponse>> postTagsMap = tagRepository.getPostIdAndTagMap(postIds);

        postList.forEach(postResponse -> {
            List<TagResponse> tags = postTagsMap.getOrDefault(postResponse.getId(), Collections.emptyList());
            postResponse.setTags(tags);
        });

        return postList;
    }

    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String title = post.getTitle();
        String content = post.getContent();
        Set<String> tagNames = post.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

        return new PostResponse(id, title, content, tagNames);
    }

    @Transactional
    public void updatePost(UpdatePostRequest request){
        try {
            Post post = postRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(EntityNotFoundException::new);

            post.setCategory(category);
            post.setTitle(request.getTitle());
            post.setContent(request.getContent());
            postAddTags(post, request.getTagNames());
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Transactional
    public void updatePostStatus(Long id){
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        post.setStatus(!post.isStatus());
    }

    public void deletePost(Long id){
        boolean existingPostCheck = postRepository.existsById(id);

        if(!existingPostCheck) { throw new EntityNotFoundException(); }

        postRepository.deleteById(id);
    }

}
