package com.study.blog.service.post;

import com.study.blog.controller.post.request.CreatePostRequest;
import com.study.blog.controller.post.request.PostListRequest;
import com.study.blog.controller.post.request.UpdatePostRequest;
import com.study.blog.service.post.response.PostListResponse;
import com.study.blog.service.post.response.PostResponse;
import com.study.blog.service.tag.response.TagResponse;
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

        Map<String, Tag> existingTagsMap = existingTags.stream()
                .collect(Collectors.toMap(Tag::getName, Function.identity()));

        Set<Tag> newTags = new HashSet<>();
        for (String tagName : tagNames) {
            if (existingTagsMap.containsKey(tagName)) {
                post.getTags().add(existingTagsMap.get(tagName));
            }
            else {
                newTags.add(new Tag(tagName));
            }
        }
        tagRepository.saveAll(newTags)
                .forEach(post.getTags()::add);
    }

    public void createPost(CreatePostRequest request){
        Category category = categoryRepository.findByIdOrThrow(request.getCategoryId());

        Post post = new Post(category, request.getTitle(), request.getContent());

        if(Optional.ofNullable(request.getTagNames()).isPresent()){
            postAddTags(post, request.getTagNames());
        }
        postRepository.save(post);
    }

    public Page<PostListResponse> getPostList(PostListRequest request, Pageable pageable){

        Set<Long> searchCategoryIds = request.getSearchCategoryIds();
        String searchKeyword = request.getSearchKeyword();
        Boolean searchStatus = request.getSearchStatus();

        Page<PostListResponse> postList = postRepository.getPostAndCommentCountList(searchCategoryIds, searchKeyword, searchStatus, pageable);
        Set<Long> postIds = postList.getContent().stream().map(PostListResponse::getId).collect(Collectors.toSet());

        Map<Long, List<TagResponse>> postTagsMap = tagRepository.getPostIdAndTagMap(postIds);

        postList.forEach(postResponse -> {
            List<TagResponse> tags = postTagsMap.getOrDefault(postResponse.getId(), Collections.emptyList());
            postResponse.setTags(tags);
        });

        return postList;
    }

    public PostResponse getPost(Long id){
        Post post = postRepository.findByIdOrThrow(id);
        String title = post.getTitle();
        String content = post.getContent();
        Set<String> tagNames = post.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

        return new PostResponse(id, title, content, tagNames);
    }

    @Transactional
    public void updatePost(UpdatePostRequest request){
        Post post = postRepository.findByIdOrThrow(request.getId());
        Category category = categoryRepository.findByIdOrThrow(request.getCategoryId());

        post.setCategory(category);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        postAddTags(post, request.getTagNames());
    }

    @Transactional
    public void updatePostStatus(Long id){
        Post post = postRepository.findByIdOrThrow(id);
        post.setStatus(!post.isStatus());
    }

    public void deletePost(Long id){
        Post post = postRepository.findByIdOrThrow(id);
        postRepository.delete(post);
    }

}