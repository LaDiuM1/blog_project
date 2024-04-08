package com.study.blog.domain.post;

import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.repository.PostRepository;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.post.response.PostResponse;
import com.study.blog.domain.tag.response.TagResponse;
import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.tag.repository.Tag;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
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

    private final PostCreator postCreator;
    private final PostTagUpdater postTagUpdater;

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    public void createPost(CreatePostRequest request){
        Long createdPostId = postCreator.createPost(request);
        postTagUpdater.postAddTags(createdPostId, request.getTagNames());
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
            Post post = postRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(EntityNotFoundException::new);

            post.updatePost(category, request.getTitle(), request.getContent());
    }

    @Transactional
    public void updatePostStatus(Long id){
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        post.switchStatus();
    }

    public void deletePost(Long id){
        boolean existingPostCheck = postRepository.existsById(id);

        if(!existingPostCheck) { throw new EntityNotFoundException(); }

        postRepository.deleteById(id);
    }

}
